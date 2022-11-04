package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.AllArgsConstructor;
import org.ibs.application.IExerciseService;
import org.ibs.application.dto.categorydto.SaveCategoryExercise;
import org.ibs.application.dto.exercisedto.GetExercise;
import org.ibs.application.dto.exercisedto.SaveExercise;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
     * Searches the database for an Exercise with the given id and returns it if it exists.
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
                throw new Exception("Document reference did not return a result");
            }
        } catch (Exception e) {
            throw new Exception("Exercise could not be found due to an error", e);
        }
    }

    @Override
    public List<GetExercise> getExerciseDataByCategory(String categoryId) throws Exception {
        try {
            ApiFuture<QuerySnapshot> future = db.collection("exercises").whereEqualTo("category", categoryId).get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            ArrayList<GetExercise> exerciseArrayList = new ArrayList<>();
            for (DocumentSnapshot document : documents) {
                exerciseArrayList.add(document.toObject(GetExercise.class));
            }
            return exerciseArrayList;
        } catch (Exception e) {
            throw new Exception("Exercises could not be found due to an error", e);
        }
    }

    /**
     * Saves the given Exercise in the database.
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

    /**
     * Saves the Exercise collection to the given category.
     * @param saveCategoryExercise
     * @return the saved Exercise data
     * @throws Exception
     */
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
            throw new Exception("Exercise was not persisted in category due to an error", e);
        }
    }

    /**
     * Updates the given exercise in the database
     * @param getExercise
     * @return the updated exercise
     * @throws Exception
     */
    @Override
    public GetExercise updateExercise(GetExercise getExercise) throws Exception {
        try {
            DocumentReference docRef = db.collection("exercises").document(getExercise.id);
            Map<String, Object> data = new HashMap<>();
            data.put("name", getExercise.name);
            docRef.update(data);
            return getExercise;
        } catch (Exception e) {
            throw new Exception("Exercise was not updated due to an error", e);
        }
    }

    /**
     * Updates the Exercise data in a category
     * @param saveCategoryExercise
     * @return
     * @throws Exception
     */
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
            throw new Exception("Exercise was not updated in category due to an error", e);
        }
    }

    /**
     * Deletes the Exercise entity with the given id.
     *
     * @param id
     * @return true if the operation succeeded
     * @throws Exception
     */
    @Override
    public boolean deleteExercise(String id) throws Exception {
        try {
            db.collection("exercises")
                    .document(id)
                    .delete();
            return true;
        } catch (Exception e) {
            throw new Exception("Exercise could not be deleted due to an error", e);
        }
    }

    /**
     * removes the exercise data from a category
     * @param exerciseId
     * @param categoryId
     * @return true if the operation succeeded
     * @throws Exception
     */
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
            throw new Exception("exercise could not be removed from category due to an error", e);
        }
    }
}
