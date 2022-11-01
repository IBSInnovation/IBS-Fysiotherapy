package org.ibs.application;

import org.ibs.application.dto.categorydto.GetCategory;
import org.ibs.application.dto.categorydto.GetCategoryMeasurement;
import org.ibs.application.dto.categorydto.SaveCategory;

import java.util.List;

public interface ICategoryService {
    GetCategory getCategoryData(String id) throws Exception;
    List<GetCategory> getAll() throws Exception;
    GetCategory saveCategory(SaveCategory saveCategory) throws Exception;
    GetCategory updateCategory(GetCategory getCategory) throws Exception;
    boolean deleteCategory(String id) throws Exception;
    List<GetCategoryMeasurement> getCategoryExerciseData(String id) throws Exception;
}
