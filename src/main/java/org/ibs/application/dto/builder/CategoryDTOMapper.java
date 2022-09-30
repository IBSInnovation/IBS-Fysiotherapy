package org.ibs.application.dto.builder;

import lombok.AllArgsConstructor;
import org.ibs.application.dto.CategoryDTO;
import org.ibs.application.service.ExerciseService;
import org.ibs.domain.Category;
import org.ibs.domain.Exercise;
import org.ibs.utils.DTOMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CategoryDTOMapper implements DTOMapper<CategoryDTO, Category> {
    private final ExerciseService exerciseService;
    @Override
    public CategoryDTO toDTO(Category o) {
        return CategoryDTO.builder()
                .id(o.getId())
                .name(o.getName())
                .exerciseIds(o.getExercises().stream().map(Exercise::getId).collect(Collectors.toList()))
                .build();
    }

    @Override
    public Category fromDTO(CategoryDTO o) {
        return Category.builder()
                .id(o.id)
                .name(o.name)
                .exercises(o.exerciseIds.stream().map(exerciseService::getById).collect(Collectors.toList()))
                .build();
    }
}
