package org.ibs.application;

import org.ibs.application.dto.exercisedto.GetExercise;
import org.ibs.application.dto.exercisedto.SaveExercise;

import java.util.List;

public interface IExerciseService {
    GetExercise getById(String id) throws Exception;
    List<GetExercise> getAll() throws Exception;
    SaveExercise saveExercise(SaveExercise saveExercise) throws Exception;
    boolean deleteCategory(String id) throws Exception;
}
