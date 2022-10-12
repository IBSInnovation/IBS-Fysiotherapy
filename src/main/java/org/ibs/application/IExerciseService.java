package org.ibs.application;

import org.ibs.application.dto.exercisedto.AskAllExercise;
import org.ibs.application.dto.exercisedto.AskExercise;
import org.ibs.application.dto.exercisedto.GetExercise;
import org.ibs.application.dto.exercisedto.SaveExercise;

import java.util.List;

public interface IExerciseService {
    GetExercise getById(AskExercise askExercise) throws Exception;
    List<GetExercise> getAll(AskAllExercise askAllExercise) throws Exception;
    SaveExercise saveExercise(SaveExercise saveExercise) throws Exception;
    boolean deleteExercise(AskExercise askExercise) throws Exception;
}
