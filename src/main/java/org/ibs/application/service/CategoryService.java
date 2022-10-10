package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.ibs.application.ICategoryService;
import org.ibs.application.dto.SaveCategory;
import org.ibs.data.PersistCategory;
import org.ibs.domain.Category;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryService implements ICategoryService {
    private final Firestore db;

    public CategoryService() {
        db = FirestoreClient.getFirestore();
    }

    /**
     * Searches the database for a Category entity with the given id and returns it if it exists.
     * @param id
     * @return Category of given id
     * @throws Exception
     */
    @Override
    public Category getById(String id) throws Exception {
        try {
            DocumentReference documentReference = db.collection("category").document(id);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                return document.toObject(Category.class);
            }

//            TODO add costum errors
            else {
                throw new Exception();
            }
        } catch (Exception e) {
//            misschien een LOG library zoals log4j
            throw new Exception("Category could not be found due to an error", e);
        }

    }

    /**
     * Searches the database for all Category entities and returns them.
     * @return List of Category entities
     * @throws Exception
     */
    @Override
    public List<Category> getAll() throws Exception {
        try {
            ApiFuture<QuerySnapshot> future = db.collection("category").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            List<Category> categoryList = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) {
                categoryList.add(document.toObject(Category.class));
            }

            return categoryList;
        } catch (Exception e) {
            throw new Exception("Categories could not be found due to an error", e);
        }
    }

    /**
     * Saves and updates the given Category entity in the database.
     * @param saveCategory
     * @return The saved Category entity
     * @throws Exception
     */
    @Override
    public SaveCategory saveCategory(SaveCategory saveCategory) throws Exception {
        try {
            PersistCategory category = PersistCategory.toPersistCategory(saveCategory);

            ApiFuture<WriteResult> collectionsApiFuture = db.collection("collection").document(category.getId()).set(category);

            // TODO: log dit
            collectionsApiFuture.get().getUpdateTime().toString();

            //TODO: misschien het nieuwe id in de dto zetten
            return saveCategory;
        } catch (Exception e) {
//            misschien een Log library zoals log4j
            throw new Exception("Category was not persisted due to an error", e);
        }
    }

    /**
     * Deletes the Category entity with the given id.
     * @param id
     * @return true if the operation succeeded
     * @throws Exception
     */
    @Override
    public boolean deleteCategory(String id) throws Exception {
        try {
            ApiFuture<WriteResult> writeResult = db.collection("category").document(id).delete();
            // TODO: log dit
            writeResult.get().getUpdateTime().toString();
            return true;
        } catch (Exception e) {
            throw new Exception("Category could not be deleted due to an error", e);
        }
    }
}
