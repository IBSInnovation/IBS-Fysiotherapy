package org.ibs.application;

import org.ibs.application.dto.categorydto.GetCategory;
import org.ibs.application.dto.categorydto.SaveCategory;
import org.ibs.domain.Category;

import java.util.List;

public interface ICategoryService {
    GetCategory getById(String id) throws Exception;
    List<GetCategory> getAll() throws Exception;
    Category saveCategory(SaveCategory saveCategory) throws Exception;
    boolean deleteCategory(String id) throws Exception;
}
