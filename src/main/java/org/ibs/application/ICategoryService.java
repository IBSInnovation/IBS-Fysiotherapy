package org.ibs.application;

import org.ibs.application.dto.CategoryDTO;
import org.ibs.domain.Category;

import java.util.List;

public interface ICategoryService {
    Category getById(long id) throws Exception;
    List<Category> getAll() throws Exception;
    Category persistCategory(CategoryDTO categoryDTO) throws Exception;
    boolean deleteCategory(long id) throws Exception;
}
