package org.ibs.application;

import org.ibs.application.dto.SaveCategory;
import org.ibs.domain.Category;

import java.util.List;

public interface ICategoryService {
    Category getById(String id) throws Exception;
    List<Category> getAll() throws Exception;
    SaveCategory saveCategory(SaveCategory saveCategory) throws Exception;
    boolean deleteCategory(String id) throws Exception;
}
