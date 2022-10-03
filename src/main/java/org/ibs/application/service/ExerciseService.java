package org.ibs.application.service;

import lombok.AllArgsConstructor;
import org.ibs.application.IExerciseService;
import org.ibs.data.ExerciseRepository;
import org.ibs.domain.Exercise;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ExerciseService implements IExerciseService {
    private final ExerciseRepository exerciseRepository;

    /**
     * Seraches the database for an Exercise entity with the given id and returns it if it exists.
     * @param id
     * @return Exercise of given id
     * @throws Exception
     */
    @Override
    public Exercise getById(long id) throws Exception {
        try {
            return exerciseRepository.findById(id).orElseThrow(Exception::new);
        } catch (Exception e) {
            throw new Exception("Exercise could not be found due to an error", e);
        }
    }

    /**
     * Searches the database for all Exercise entities and returns them.
     * @return List of Exercise entities
     * @throws Exception
     */
    @Override
    public List<Exercise> getAll() throws Exception {
        try {
            return exerciseRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Exercises could not be found due to an error", e);
        }
    }

    /**
     * Saves and updates the given Exercise entity in the database.
     * @param exercise
     * @return The saved Exercise entity
     * @throws Exception
     */
    @Override
    public Exercise persistExercise(Exercise exercise) throws Exception {
        try {
            return exerciseRepository.save(exercise);
        } catch (Exception e) {
            throw new Exception("Exercise was not persisted due to an error", e);
        }
    }

    /**
     * Deletes the Exercise entity with the given id.
     * @param id
     * @return true if the operation succeeded
     * @throws Exception
     */
    @Override
    public boolean deleteCategory(long id) throws Exception {
        try {
            exerciseRepository.delete(exerciseRepository.findById(id).orElseThrow(Exception::new));
            return true;
        } catch (Exception e) {
            throw new Exception("Exercise could not be deleted due to an error", e);
        }
    }
}
