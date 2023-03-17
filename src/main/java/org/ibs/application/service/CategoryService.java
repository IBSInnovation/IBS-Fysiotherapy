package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.ibs.application.ICategoryService;
import org.ibs.application.dto.categorydto.GetCategory;
import org.ibs.application.dto.categorydto.SaveCategory;
import org.ibs.data.CategoryRepository;
import org.ibs.data.PersistCategory;
import org.ibs.domain.Category;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository ;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Searches the database for a Category entity with the given id and returns it if it exists.
     * @param id
     * @return Category of given id
     * @throws Exception
     */
    @Override
    public GetCategory getById(String id) throws Exception {
        try {
            Category category = categoryRepository.getById(Long.parseLong(id));
            return new GetCategory(category);
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
    public List<GetCategory> getAll() throws Exception {
        try {
            List<Category> categories = categoryRepository.findAll();

            List<GetCategory> categoryList = new ArrayList<>();
            for (Category category : categories) {
                categoryList.add(new GetCategory(category));
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
    public Category saveCategory(SaveCategory saveCategory) throws Exception {
        try {
            Category category = new Category(saveCategory.name);
            this.categoryRepository.save(category);


            // TODO: kijken of dit de beste oplossing is, category is namelijk leeg
            /*ApiFuture<WriteResult> collectionsApiFuture = db.collection("category").document(saveCategory.name).set(category);

            // TODO: log dit
            collectionsApiFuture.get().getUpdateTime().toString();

            //TODO: misschien het nieuwe id in de dto zetten*/
            return category;
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
            Category category = categoryRepository.getById(Long.parseLong(id));
            categoryRepository.delete(category);
/*            ApiFuture<WriteResult> writeResult = db.collection("category").document(id).delete();
            // TODO: log dit
            writeResult.get().getUpdateTime().toString();*/
            return true;
        } catch (Exception e) {
            throw new Exception("Category could not be deleted due to an error", e);
        }
    }
}
