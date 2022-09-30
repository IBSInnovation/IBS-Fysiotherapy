package org.ibs.application.service;

import lombok.AllArgsConstructor;
import org.ibs.application.ICategoryService;
import org.ibs.application.dto.CategoryDTO;
import org.ibs.application.dto.builder.CategoryDTOMapper;
import org.ibs.data.CategoryRepository;
import org.ibs.domain.Category;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

// tests nog niet gwschreven omdat ik niet zeker weet of we ook zo de try catch
// willen uitvoeren.

@Service
@Transactional
@AllArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryDTOMapper categoryDTOMapper;
    private final CategoryRepository categoryRepository;
    @Override
    public Category getById(long id) throws Exception {
        try {
            return categoryRepository.getById(id);
        } catch (Exception e) {
//            misschien een LOG library zoals log4j
            throw new Exception("Category could not be found due to an error", e);
        }

    }

    @Override
    public List<Category> getAll() throws Exception {
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Categories could not be found due to an error", e);
        }
    }

    @Override
    public Category persistCategory(CategoryDTO categoryDTO) throws Exception {
        try {
            return categoryRepository.save(categoryDTOMapper.fromDTO(categoryDTO));
        } catch (Exception e) {
//            misschien een Log library zoals log4j
            throw new Exception("Category was not persisted due to an error", e);
        }
    }

    @Override
    public boolean deleteCategory(long id) throws Exception {
        try {
            categoryRepository.delete(categoryRepository.getById(id));
            return true;
        } catch (Exception e) {
            throw new Exception("Category could not be deleted due to an error", e);
        }
    }
}
