package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.AllArgsConstructor;
import org.ibs.application.IExerciseService;
import org.ibs.application.dto.exercisedto.AskAllExercise;
import org.ibs.application.dto.exercisedto.AskExercise;
import org.ibs.application.dto.exercisedto.GetExercise;
import org.ibs.application.dto.exercisedto.SaveExercise;
import org.ibs.data.PersistExercise;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ExerciseService implements IExerciseService {
    private final Firestore db;

    public ExerciseService() {
        this.db = FirestoreClient.getFirestore();
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
            DocumentReference documentReference = db
                    .collection("category").document(askExercise.categoryId)
                    .collection("exercises").document(askExercise.id);

            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                return document.toObject(GetExercise.class);
            }

            else {
                throw new Exception("Document doesn't exist");
            }
        } catch (Exception e) {
            throw new Exception("Exercise could not be found due to an error", e);
        }
    }

    /**
     * Searches the database for all Exercise entities and returns them.
     * @return List of Exercise entities
     * @throws Exception
     */
//    TODO: kan verwijderd worden
    @Override
    public List<GetExercise> getAll(AskAllExercise askAllExercise) throws Exception {
        try {
            ApiFuture<QuerySnapshot> future = db
                    .collection("category").document(askAllExercise.categoryId)
                    .collection("exercises").get();

            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            List<GetExercise> exerciseList = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) {
                exerciseList.add(document.toObject(GetExercise.class));
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
//    TODO: zorg ervoor dat het ook in category word geupdate
    @Override
    public SaveExercise saveExercise(SaveExercise saveExercise) throws Exception {
        try {
            PersistExercise persistExercise = PersistExercise.toPersistExercise(saveExercise);

            ApiFuture<WriteResult> exerciseResult = db
                    .collection("category").document(saveExercise.categoryId)
                    .collection("exercises").document(saveExercise.id).set(persistExercise);


            exerciseResult.get().getUpdateTime().toString();

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
//    TODO: zorg ervoor dat het ook bij exercise verwijderd word.
    @Override
    public boolean deleteExercise(AskExercise askExercise) throws Exception {
        try {
            ApiFuture<WriteResult> writeResult = db
                    .collection("category").document(askExercise.categoryId)
                    .collection("exercises").document(askExercise.id).delete();
            writeResult.get().getUpdateTime().toString();
            return true;
        } catch (Exception e) {
            throw new Exception("Exercise could not be deleted due to an error", e);
        }
    }
}
