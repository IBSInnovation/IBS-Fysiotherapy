package org.ibs.application.service;

import lombok.AllArgsConstructor;
import org.ibs.application.ICategoryService;
import org.ibs.data.CategoryRepository;
import org.ibs.domain.Category;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    /**
     * Searches the database for a Category entity with the given id and returns it if it exists.
     * @param id
     * @return Category of given id
     * @throws Exception
     */
    @Override
    public Category getById(long id) throws Exception {
        try {
            return categoryRepository.findById(id).orElseThrow(Exception::new);
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
            return categoryRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Categories could not be found due to an error", e);
        }
    }

    /**
     * Saves and updates the given Category entity in the database.
     * @param category
     * @return The saved Category entity
     * @throws Exception
     */
    @Override
    public Category persistCategory(Category category) throws Exception {
        try {
            return categoryRepository.save(category);
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
    public boolean deleteCategory(long id) throws Exception {
        try {
            categoryRepository.delete(categoryRepository.findById(id).orElseThrow(Exception::new));
            return true;
        } catch (Exception e) {
            throw new Exception("Category could not be deleted due to an error", e);
        }
    }
}
