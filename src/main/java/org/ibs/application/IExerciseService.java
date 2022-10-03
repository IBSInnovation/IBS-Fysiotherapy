package org.ibs.application;

import org.ibs.domain.Exercise;

import java.util.List;

public interface IExerciseService {
    Exercise getById(long id) throws Exception;
    List<Exercise> getAll() throws Exception;
    Exercise persistExercise(Exercise exercise) throws Exception;
    boolean deleteCategory(long id) throws Exception;
}
