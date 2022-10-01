package org.ibs.application;

import org.ibs.domain.Category;

import java.util.List;

public interface ICategoryService {
    Category getById(long id) throws Exception;
    List<Category> getAll() throws Exception;
    Category persistCategory(Category category) throws Exception;
    boolean deleteCategory(long id) throws Exception;
}
