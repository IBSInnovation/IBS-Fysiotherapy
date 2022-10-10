package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.AllArgsConstructor;
import org.ibs.application.IExerciseService;
import org.ibs.application.dto.SaveExercise;
import org.ibs.data.PersistExercise;
import org.ibs.domain.Exercise;
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
     * @param id
     * @return Exercise of given id
     * @throws Exception
     */
    @Override
    public Exercise getById(String id) throws Exception {
        try {
            DocumentReference documentReference = db.collection("exercise").document(id);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                return document.toObject(Exercise.class);
            }

//            TODO add costum errors
            else {
                throw new Exception();
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
    @Override
    public List<Exercise> getAll() throws Exception {
        try {
            ApiFuture<QuerySnapshot> future = db.collection("exercise").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            List<Exercise> exerciseList = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) {
                exerciseList.add(document.toObject(Exercise.class));
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
            PersistExercise persistExercise = PersistExercise.toPersistExercise(saveExercise);
            ApiFuture<WriteResult> collectionsApiFuture = db.collection("exercise").document(persistExercise.getId()).set(persistExercise);

            // TODO: log dit
            collectionsApiFuture.get().getUpdateTime().toString();

            //TODO: misschien het nieuwe id in de dto zetten
            return saveExercise;

        } catch (Exception e) {
            throw new Exception("Exercise was not persisted due to an error", e);
        }
    }

    /**
     * Deletes the Exercise entity with the given id.
     * @param id
     * @return true if the operation succeeded
     * @throws Exception
     */
    @Override
    public boolean deleteCategory(String id) throws Exception {
        try {
            ApiFuture<WriteResult> writeResult = db.collection("physiotherapist").document(id).delete();
            // TODO: log dit
            writeResult.get().getUpdateTime().toString();
            return true;
        } catch (Exception e) {
            throw new Exception("Exercise could not be deleted due to an error", e);
        }
    }
}
