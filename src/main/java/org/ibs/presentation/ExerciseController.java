package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IExerciseService;
import org.ibs.application.IJoinService;
import org.ibs.application.dto.PlaceholderDTO;
import org.ibs.application.dto.exercisedto.GetExercise;
import org.ibs.application.dto.exercisedto.SaveExercise;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://127.0.0.1:5500/")
@RestController
@RequestMapping("/exercise")
@AllArgsConstructor
public class ExerciseController {
    private final IExerciseService exerciseService;
    private final IJoinService joinService;

    @GetMapping("/{id}")
    public GetExercise getExerciseById(@PathVariable String id) throws Exception {
        return exerciseService.getExerciseData(id);
    }

    @PostMapping
    public GetExercise createExercise(@RequestBody SaveExercise saveExercise) throws Exception {
        return exerciseService.saveExercise(saveExercise);
    }

    @PatchMapping
    public PlaceholderDTO updateExercise(@RequestBody GetExercise getExercise) {
        return joinService.updateExercise(getExercise);
    }

    @DeleteMapping("/{id}")
    public boolean deleteExercise(@PathVariable String id) throws Exception {
        return exerciseService.deleteExercise(id);
    }
}
