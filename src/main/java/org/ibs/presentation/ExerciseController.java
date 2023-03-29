package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IExerciseService;
import org.ibs.application.dto.exercisedto.AskAllExercise;
import org.ibs.application.dto.exercisedto.AskExercise;
import org.ibs.application.dto.exercisedto.GetExercise;
import org.ibs.application.dto.exercisedto.SaveExercise;
import org.ibs.application.service.ExerciseService;
import org.ibs.domain.Exercise;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
@AllArgsConstructor
public class ExerciseController {
    private final ExerciseService exerciseService;

    @GetMapping
    public GetExercise getExerciseById(@RequestBody AskExercise askExercise) throws Exception {
        return exerciseService.getById(askExercise);
    }

    @GetMapping("/all")
    public List<GetExercise> getAllExercisesFromCategory(@RequestBody AskAllExercise askAllExercise) throws Exception {
        return exerciseService.getAll(askAllExercise);
    }

    @PostMapping
    public SaveExercise createExercise(@RequestBody SaveExercise saveExercise) throws Exception {
        return exerciseService.saveExercise(saveExercise);
    }

    @PatchMapping
    public Exercise updateExercise(@PathVariable String id, @RequestBody SaveExercise saveExercise) throws Exception {
        return exerciseService.updateExercise(id, saveExercise);
    }

    @DeleteMapping()
    public boolean deleteExercise(@RequestBody AskExercise askExercise) throws Exception {
        return exerciseService.deleteExercise(askExercise);
    }
}
