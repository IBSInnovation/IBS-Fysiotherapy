package org.ibs.application.service;

import lombok.AllArgsConstructor;
import org.ibs.application.IExerciseService;
import org.ibs.application.dto.ExerciseDTO;
import org.ibs.domain.Exercise;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
@AllArgsConstructor
public class ExerciseService implements IExerciseService {
    @Override
    public Exercise getById(long id) {
        return null;
    }

    @Override
    public ArrayList<Exercise> getAll() {
        return null;
    }

    @Override
    public Exercise persistExercise(ExerciseDTO exerciseDTO) {
        return null;
    }

    @Override
    public boolean deleteCategory(long id) {
        return false;
    }
}
