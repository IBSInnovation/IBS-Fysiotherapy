package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IExerciseService;
import org.ibs.application.dto.SaveExercise;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exercise")
@AllArgsConstructor
public class ExerciseController {
    private final IExerciseService exerciseService;

//    @GetMapping("/{id}")
//    public ExerciseDTO getExerciseById(@PathVariable long id) throws Exception {
//        return exerciseDTOMapper.toDTO(exerciseService.getById(id));
//    }
//
//    @GetMapping
//    public List<ExerciseDTO> getAllExercises() throws Exception {
//        return exerciseDTOMapper.toMultipleDTO(exerciseService.getAll());
//    }

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
