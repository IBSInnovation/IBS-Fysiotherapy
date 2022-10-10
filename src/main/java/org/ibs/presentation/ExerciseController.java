package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IExerciseService;
import org.ibs.application.dto.ExerciseDTO;
import org.ibs.application.dto.mapper.ExerciseDTOMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
@AllArgsConstructor
public class ExerciseController {
    private final IExerciseService exerciseService;
    private final ExerciseDTOMapper exerciseDTOMapper;

    @GetMapping("/{id}")
    public ExerciseDTO getExerciseById(@PathVariable long id) throws Exception {
        return exerciseDTOMapper.toDTO(exerciseService.getById(id));
    }

    @GetMapping
    public List<ExerciseDTO> getAllExercises() throws Exception {
        return exerciseDTOMapper.toMultipleDTO(exerciseService.getAll());
    }

    @PostMapping
    public ExerciseDTO createExercise(@RequestBody ExerciseDTO exerciseDTO) throws Exception {
        return exerciseDTOMapper.toDTO(exerciseService.persistExercise(exerciseDTOMapper.fromDTO(exerciseDTO)));
    }

    @PatchMapping
    public ExerciseDTO updateExercise(@RequestBody ExerciseDTO exerciseDTO) throws Exception {
        return exerciseDTOMapper.toDTO(exerciseService.persistExercise(exerciseDTOMapper.fromDTO(exerciseDTO)));
    }

    @DeleteMapping("/{id}")
    public boolean deleteExercise(@PathVariable long id) throws Exception {
        return exerciseService.deleteCategory(id);
    }
}
