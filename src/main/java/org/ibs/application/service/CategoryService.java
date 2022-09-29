package org.ibs.application.service;

import lombok.AllArgsConstructor;
import org.ibs.application.ICategoryService;
import org.ibs.application.dto.CategoryDTO;
import org.ibs.domain.Category;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
@AllArgsConstructor
public class CategoryService implements ICategoryService {
    @Override
    public Category getById(long id) {
        return null;
    }

    @Override
    public ArrayList<Category> getAll() {
        return null;
    }

    @Override
    public Category persistCategory(CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public boolean deleteCategory(long id) {
        return false;
    }
}
