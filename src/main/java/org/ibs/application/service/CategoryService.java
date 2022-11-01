package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.ibs.application.ICategoryService;
import org.ibs.application.dto.categorydto.GetCategory;
import org.ibs.application.dto.categorydto.GetCategoryMeasurement;
import org.ibs.application.dto.categorydto.SaveCategory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.ibs.application.service.JoinService.deleteSubCollections;

@Service
@Transactional
public class CategoryService implements ICategoryService {
    private final Firestore db;

    public CategoryService() {
        db = FirestoreClient.getFirestore();
    }

    /**
     * Searches the database for a Category with the given id and returns it if it exists.
     *
     * @param id
     * @return Category of given id
     * @throws Exception
     */
    @Override
    public GetCategory getCategoryData(String id) throws Exception {
        try {
            DocumentReference documentReference = db.collection("category").document(id);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                GetCategory dto = document.toObject(GetCategory.class);
                dto.id = id;
                return dto;
            } else {
                throw new Exception("Document reference did not return a result");
            }
        } catch (Exception e) {
            throw new Exception("Category could not be found due to an error", e);
        }
    }

    /**
     * Gets the Exercise collection of the given Category
     *
     * @param id
     * @return List of Exercise data of a given Category
     * @throws Exception
     */
    @Override
    public List<GetCategoryMeasurement> getCategoryExerciseData(String id) throws Exception {
        try {
            ApiFuture<QuerySnapshot> future = db.collection("cateogry")
                    .document(id).collection("exercises").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            List<GetCategoryMeasurement> dtoList = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) {
                dtoList.add(document.toObject(GetCategoryMeasurement.class));
            }
            return dtoList;
        } catch (Exception e) {
            throw new Exception("The exercise data of this category could not be found due to an error", e);
        }
    }

    /**
     * Searches the database for all Categories and returns them.
     *
     * @return List of Categories
     * @throws Exception
     */
    @Override
    public List<GetCategory> getAll() throws Exception {
        try {
            ApiFuture<QuerySnapshot> future = db.collection("category").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            List<GetCategory> categoryList = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) {
                GetCategory dto = document.toObject(GetCategory.class);
                dto.id = document.getId();
                categoryList.add(dto);
            }
            return categoryList;
        } catch (Exception e) {
            throw new Exception("Categories could not be found due to an error", e);
        }
    }

    /**
     * Saves the given Category in the database.
     *
     * @param saveCategory
     * @return The saved Category
     * @throws Exception
     */
    @Override
    public GetCategory saveCategory(SaveCategory saveCategory) throws Exception {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("name", saveCategory.name);
            ApiFuture<DocumentReference> addedDocRef = db.collection("category").add(data);

            return new GetCategory(
                    addedDocRef.get().getId(),
                    saveCategory.name
            );
        } catch (Exception e) {
            throw new Exception("Category was not persisted due to an error", e);
        }
    }

    /**
     * Updates the given Category in the database.
     *
     * @param getCategory
     * @return
     * @throws Exception
     */
    @Override
    public GetCategory updateCategory(GetCategory getCategory) throws Exception {
        try {
            DocumentReference docRef = db.collection("category").document(getCategory.id);
            Map<String, Object> data = new HashMap<>();
            data.put("name", getCategory.name);
            docRef.update(data);
            return getCategory;
        } catch (Exception e) {
            throw new Exception("Category was not updated due to an error", e);
        }
    }

    /**
     * Deletes the Category with the given id.
     *
     * @param id
     * @return true if the operation succeeded
     * @throws Exception
     */
    @Override
    public boolean deleteCategory(String id) throws Exception {
        try {
            deleteSubCollections(db.collection("category").document(id).collection("exercise"), 10);
            db.collection("category").document(id).delete();
            return true;
        } catch (Exception e) {
            throw new Exception("Category could not be deleted due to an error", e);
        }
    }
}
