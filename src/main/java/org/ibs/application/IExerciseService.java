package org.ibs.application;

import org.ibs.application.dto.categorydto.SaveCategoryExercise;
import org.ibs.application.dto.exercisedto.AskAllExercise;
import org.ibs.application.dto.exercisedto.AskExercise;
import org.ibs.application.dto.exercisedto.GetExercise;
import org.ibs.application.dto.exercisedto.SaveExercise;

import java.util.List;

public interface IExerciseService {
    GetExercise getById(String id) throws Exception;
    GetExercise saveExercise(SaveExercise saveExercise) throws Exception;

    SaveCategoryExercise saveExerciseToCategory(SaveCategoryExercise saveCategoryExercise) throws Exception;

    SaveCategoryExercise updateExerciseToCategory(SaveCategoryExercise saveCategoryExercise) throws Exception;

    boolean deleteExercise(AskExercise askExercise) throws Exception;
}
