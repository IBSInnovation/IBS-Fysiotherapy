package org.ibs.application;

import org.ibs.application.dto.ExerciseDTO;
import org.ibs.domain.Exercise;

import java.util.ArrayList;

public interface IExerciseService {
    Exercise getById(long id);
    ArrayList<Exercise> getAll();
    Exercise persistExercise(ExerciseDTO exerciseDTO);
    boolean deleteCategory(long id);
}
