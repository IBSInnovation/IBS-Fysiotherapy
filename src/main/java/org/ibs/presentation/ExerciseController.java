package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IExerciseService;
import org.ibs.application.dto.exercisedto.GetExercise;
import org.ibs.application.dto.exercisedto.SaveExercise;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
@AllArgsConstructor
public class ExerciseController {
    private final IExerciseService exerciseService;

    @GetMapping("/{id}")
    public GetExercise getExerciseById(@PathVariable String id) throws Exception {
        return exerciseService.getById(id);
    }

    @GetMapping
    public List<GetExercise> getAllExercises() throws Exception {
        return exerciseService.getAll();
    }

    @PostMapping
    public SaveExercise createExercise(@RequestBody SaveExercise saveExercise) throws Exception {
        return exerciseService.saveExercise(saveExercise);
    }

    @PatchMapping
    public SaveExercise updateExercise(@RequestBody SaveExercise saveExercise) throws Exception {
        return exerciseService.saveExercise(saveExercise);
    }

    @DeleteMapping("/{id}")
    public boolean deleteExercise(@PathVariable String id) throws Exception {
        return exerciseService.deleteCategory(id);
    }
}
