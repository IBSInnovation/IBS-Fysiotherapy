package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.api.services.storage.Storage;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.AllArgsConstructor;
import org.ibs.application.IExerciseService;
import org.ibs.application.dto.exercisedto.AskAllExercise;
import org.ibs.application.dto.exercisedto.AskExercise;
import org.ibs.application.dto.exercisedto.GetExercise;
import org.ibs.application.dto.exercisedto.SaveExercise;
import org.ibs.data.ExcerciseRepository;
import org.ibs.data.PersistExercise;
import org.ibs.domain.Exercise;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ExerciseService implements IExerciseService {
    private ExcerciseRepository excerciseRepository;

    public ExerciseService(ExcerciseRepository excerciseRepository) {
        this.excerciseRepository= excerciseRepository;
    }

    /**
     * Seraches the database for an Exercise entity with the given id and returns it if it exists.
     * @param askExercise
     * @return Exercise of given id
     * @throws Exception
     */
    @Override
    public GetExercise getById(AskExercise askExercise) throws Exception {
        try {
            Exercise exercise = excerciseRepository.getReferenceById(Long.parseLong(askExercise.id));
            return new GetExercise(exercise.getId(), exercise.getName(), exercise.getMeasurements());
//            TODO add costum errors
        } catch (Exception e) {
            throw new Exception("Exercise could not be found due to an error", e);
        }
    }

    /**
     * Searches the database for all Exercise entities and returns them.
     * @return List of Exercise entities
     * @throws Exception
     */
    @Override
    public List<GetExercise> getAll(AskAllExercise askAllExercise) throws Exception {
        try {
            List<Exercise> exercises = excerciseRepository.findAll();
            List<GetExercise> exerciseList = new ArrayList<>();
            for(Exercise exercise : exercises){
                exerciseList.add(new GetExercise(exercise.getId(), exercise.getName(), exercise.getMeasurements()));
            }
            return exerciseList;
        } catch (Exception e) {
            throw new Exception("Exercises could not be found due to an error", e);
        }
    }

    /**
     * Saves and updates the given Exercise entity in the database.
     * @param saveExercise
     * @return The saved Exercise entity
     * @throws Exception
     */
    @Override
    public SaveExercise saveExercise(SaveExercise saveExercise) throws Exception {
        try {
            Exercise exercise = new Exercise(saveExercise.name);
            excerciseRepository.save(exercise);
            // TODO: log dit
            return saveExercise;

        } catch (Exception e) {
            throw new Exception("Exercise was not persisted due to an error", e);
        }
    }

    /**
     * Deletes the Exercise entity with the given id.
     * @param askExercise
     * @return true if the operation succeeded
     * @throws Exception
     */
    @Override
    public boolean deleteExercise(AskExercise askExercise) throws Exception {
        try {
            Exercise exercise = excerciseRepository.getById(Long.parseLong(askExercise.id));
            excerciseRepository.delete(exercise);
            /*ApiFuture<WriteResult> writeResult = db
                    .collection("category").document(askExercise.categoryId)
                    .collection("exercises").document(askExercise.id).delete();
            // TODO: log dit
            writeResult.get().getUpdateTime().toString();*/
            return true;
        } catch (Exception e) {
            throw new Exception("Exercise could not be deleted due to an error", e);
        }
    }
}
