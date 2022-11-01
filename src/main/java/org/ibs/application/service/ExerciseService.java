package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.AllArgsConstructor;
import org.ibs.application.IExerciseService;
import org.ibs.application.dto.categorydto.SaveCategoryExercise;
import org.ibs.application.dto.exercisedto.AskExercise;
import org.ibs.application.dto.exercisedto.GetExercise;
import org.ibs.application.dto.exercisedto.SaveExercise;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@AllArgsConstructor
public class ExerciseService implements IExerciseService {
    private final Firestore db;

    public ExerciseService() {
        this.db = FirestoreClient.getFirestore();
    }

    /**
     * Searches the database for an Exercise entity with the given id and returns it if it exists.
     *
     * @param id
     * @return Exercise of given id
     * @throws Exception
     */
    @Override
    public GetExercise getExerciseData(String id) throws Exception {
        try {
            DocumentReference documentReference = db.collection("exercises").document(id);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                GetExercise dto = document.toObject(GetExercise.class);
                dto.id = id;
                return dto;
            } else {
                throw new Exception("Document doesn't exist");
            }
        } catch (Exception e) {
            throw new Exception("Exercise could not be found due to an error", e);
        }
    }


    /**
     * Saves and updates the given Exercise entity in the database.
     *
     * @param saveExercise
     * @return The saved Exercise entity
     * @throws Exception
     */
    @Override
    public GetExercise saveExercise(SaveExercise saveExercise) throws Exception {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("name", saveExercise.name);
            data.put("description", saveExercise.description);
            data.put("category", saveExercise.categoryId);

            ApiFuture<DocumentReference> addedDocRef = db.collection("exercises").add(data);


            return new GetExercise(
                    addedDocRef.get().getId(),
                    saveExercise.name,
                    saveExercise.description,
                    saveExercise.categoryId
            );

        } catch (Exception e) {
            throw new Exception("Exercise was not persisted due to an error", e);
        }
    }

    @Override
    public SaveCategoryExercise saveExerciseToCategory(SaveCategoryExercise saveCategoryExercise) throws Exception {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("exercise", saveCategoryExercise.exerciseId);
            data.put("name", saveCategoryExercise.name);
            db.collection("category")
                    .document(saveCategoryExercise.categoryId)
                    .collection("exercises")
                    .document(saveCategoryExercise.exerciseId).set(data);
            return saveCategoryExercise;
        } catch (Exception e) {
            throw new Exception("Exercise was not persisted to category due to an error", e);
        }
    }

    @Override
    public GetExercise updateExercise(GetExercise getExercise) throws Exception {
        try {
            DocumentReference docRef =db.collection("exercises").document(getExercise.id);
            Map<String, Object> data = new HashMap<>();
            data.put("name", getExercise.name);
            docRef.update(data);
            return getExercise;
        } catch (Exception e) {
            throw new Exception("Exercise was not updated due to an error", e);
        }
    }

    @Override
    public SaveCategoryExercise updateExerciseToCategory(SaveCategoryExercise saveCategoryExercise) throws Exception {
        try {
            DocumentReference docRef = db
                    .collection("category")
                    .document(saveCategoryExercise.categoryId)
                    .collection("exercises")
                    .document(saveCategoryExercise.exerciseId);

            Map<String, Object> data = new HashMap<>();
            data.put("name", saveCategoryExercise.name);
            docRef.update(data);
            return saveCategoryExercise;
        } catch (Exception e) {
            throw new Exception("Exercise was not updated to category due to an error", e);
        }
    }

    /**
     * Deletes the Exercise entity with the given id.
     *
     * @param askExercise
     * @return true if the operation succeeded
     * @throws Exception
     */
    @Override
    public boolean deleteExercise(AskExercise askExercise) throws Exception {
        try {
            db.collection("exercises")
                    .document(askExercise.id)
                    .delete();
            return true;
        } catch (Exception e) {
            throw new Exception("Exercise could not be deleted due to an error", e);
        }
    }

    @Override
    public boolean removeExerciseFromCategory(String exerciseId, String categoryId) throws Exception {
        try {
            db.collection("category")
                    .document(categoryId)
                    .collection("exercises")
                    .document(exerciseId)
                    .delete();
            return true;
        } catch (Exception e) {
            throw new Exception("exercise could not be deleted due to an error", e);
        }
    }
}
