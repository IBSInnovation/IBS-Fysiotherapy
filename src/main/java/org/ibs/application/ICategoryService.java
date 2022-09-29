package org.ibs.application;

import org.ibs.application.dto.CategoryDTO;
import org.ibs.domain.Category;

import java.util.ArrayList;

public interface ICategoryService {
    Category getById(long id);
    ArrayList<Category> getAll();
    Category persistCategory(CategoryDTO categoryDTO);
    boolean deleteCategory(long id);
}
