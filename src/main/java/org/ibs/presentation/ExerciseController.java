package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IExerciseService;
import org.ibs.application.dto.exercisedto.AskExercise;
import org.ibs.application.dto.exercisedto.GetExercise;
import org.ibs.application.dto.exercisedto.SaveExercise;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://127.0.0.1:5500/")
@RestController
@RequestMapping("/exercise")
@AllArgsConstructor
public class ExerciseController {
    private final IExerciseService exerciseService;

    @GetMapping
    public GetExercise getExerciseById(@RequestBody AskExercise askExercise) throws Exception {
        return exerciseService.getExerciseData(askExercise);
    }

    @PostMapping
    public GetExercise createExercise(@RequestBody SaveExercise saveExercise) throws Exception {
        return exerciseService.saveExercise(saveExercise);
    }

    @PatchMapping
    public GetExercise updateExercise(@RequestBody SaveExercise saveExercise) throws Exception {
        return exerciseService.saveExercise(saveExercise);
    }

    @DeleteMapping()
    public boolean deleteExercise(@RequestBody AskExercise askExercise) throws Exception {
        return exerciseService.deleteExercise(askExercise);
    }
}
