package org.ibs.application;

import org.ibs.application.dto.categorydto.SaveCategoryExercise;
import org.ibs.application.dto.exercisedto.GetExercise;
import org.ibs.application.dto.exercisedto.SaveExercise;

import java.util.List;

public interface IExerciseService {
    GetExercise getExerciseData(String id) throws Exception;

    List<GetExercise> getExerciseDataByCategory(String categoryId) throws Exception;

    GetExercise saveExercise(SaveExercise saveExercise) throws Exception;

    SaveCategoryExercise saveExerciseToCategory(SaveCategoryExercise saveCategoryExercise) throws Exception;

    GetExercise updateExercise(GetExercise getExercise) throws Exception;

    SaveCategoryExercise updateExerciseToCategory(SaveCategoryExercise saveCategoryExercise) throws Exception;

    boolean deleteExercise(String id) throws Exception;

    boolean removeExerciseFromCategory(String exerciseId, String categoryId) throws Exception;
}
