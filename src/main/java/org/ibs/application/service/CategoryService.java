package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.ibs.application.ICategoryService;
import org.ibs.application.dto.categorydto.GetCategory;
import org.ibs.application.dto.categorydto.SaveCategory;
import org.ibs.data.PersistCategory;
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
//    TODO: kan verwijderd worden, zal niet meer gebruikt worden
    @Override
    public GetCategory getById(String id) throws Exception {
        try {
            DocumentReference documentReference = db.collection("category").document(id);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                return document.toObject(GetCategory.class);
            }
            else {
                throw new Exception("DocumentSnapshot does not exist");
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
//    TODO: zorg ervoor dat de juiste dingen worden terug gegeven
    @Override
    public List<GetCategory> getAll() throws Exception {
        try {
            ApiFuture<QuerySnapshot> future = db.collection("category").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            List<GetCategory> categoryList = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) {
                categoryList.add(document.toObject(GetCategory.class));
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
//    TODO: zorg ervoor dat er altijd een lege lisjt word meegegeven wanneer een category word aangemaakt
    @Override
    public SaveCategory saveCategory(SaveCategory saveCategory) throws Exception {
        try {
            PersistCategory category = PersistCategory.toPersistCategory(saveCategory);

            ApiFuture<WriteResult> collectionsApiFuture = db.collection("category").document(saveCategory.name).set(category);

            collectionsApiFuture.get().getUpdateTime().toString();

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
//    TODO: zorg ervoor dat alle exercises van deze category ook worden verwijderd
    @Override
    public boolean deleteCategory(String id) throws Exception {
        try {
            ApiFuture<WriteResult> writeResult = db.collection("category").document(id).delete();
            writeResult.get().getUpdateTime().toString();
            return true;
        } catch (Exception e) {
            throw new Exception("Category could not be deleted due to an error", e);
        }
    }
}
