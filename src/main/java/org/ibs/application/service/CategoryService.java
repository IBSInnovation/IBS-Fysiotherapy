package org.ibs.application.service;

import org.ibs.application.dto.categorydto.GetCategory;
import org.ibs.application.dto.categorydto.SaveCategory;
import org.ibs.data.CategoryRepository;
import org.ibs.domain.Category;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryService {
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

    public Category saveCategory(SaveCategory saveCategory) throws Exception {
        try {
            Category category = new Category(saveCategory.name);
            this.categoryRepository.save(category);
            return category;
        } catch (Exception e) {
//            misschien een Log library zoals log4j
            throw new Exception("Category was not persisted due to an error", e);
        }
    }

    public Category updateCategory(String id, SaveCategory saveCategory) throws Exception {
        try {
            Category category = categoryRepository.findById(Long.parseLong(id)).get();
            category.setName(saveCategory.name);
            this.categoryRepository.save(category);
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

    public boolean deleteCategory(String id) throws Exception {
        try {
            Category category = categoryRepository.getById(Long.parseLong(id));
            categoryRepository.delete(category);
            return true;
        } catch (Exception e) {
            throw new Exception("Category could not be deleted due to an error", e);
        }
    }
}
