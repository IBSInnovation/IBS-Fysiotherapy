package org.ibs.application;

import org.ibs.application.dto.SaveExercise;
import org.ibs.domain.Exercise;

import java.util.List;

public interface IExerciseService {
    Exercise getById(String id) throws Exception;
    List<Exercise> getAll() throws Exception;
    SaveExercise saveExercise(SaveExercise saveExercise) throws Exception;
    boolean deleteCategory(String id) throws Exception;
}
