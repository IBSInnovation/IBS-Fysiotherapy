package org.ibs.application;

import org.ibs.application.service.ExerciseService;
import org.ibs.data.ExerciseRepository;
import org.ibs.domain.Exercise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ExerciseServiceTest {
    ExerciseService exerciseService;
    ExerciseRepository mockExerciseRepository;

    @BeforeEach
    public void init() {
        mockExerciseRepository = mock(ExerciseRepository.class);
        exerciseService = new ExerciseService(mockExerciseRepository);
    }

    //    getbyid
    @Test
    public void getByIdExistingExercise() throws Exception {
        Exercise exercise = Exercise.builder().id(1L).name("testname").build();
        when(mockExerciseRepository.findById(anyLong())).thenReturn(Optional.ofNullable(exercise));
        Exercise ex = exerciseService.getById(1L);
        verify(mockExerciseRepository, times(1)).findById(anyLong());
        assertEquals(1L, ex.getId());
        assertEquals("testname", ex.getName());
    }

    @Test
    public void getByIdNonExistingExercise() {
        assertThrows(Exception.class, () -> exerciseService.getById(1L));
    }

//    getall
    @Test
    public void getAllExisting() throws Exception {
        Exercise exercise1 = Exercise.builder().id(1L).name("testname1").build();
        Exercise exercise2 = Exercise.builder().id(2L).name("testname2").build();
        Exercise exercise3 = Exercise.builder().id(3L).name("testname3").build();
        List<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(exercise1);
        exerciseList.add(exercise2);
        exerciseList.add(exercise3);
        when(mockExerciseRepository.findAll()).thenReturn(exerciseList);
        verify(mockExerciseRepository, times(1)).findAll();
        assertEquals(exerciseList, exerciseService.getAll());
    }

//    persistexercise
    @Test
    public void persistExercise() throws Exception {
        Exercise exercise = Exercise.builder().id(1L).name("testname").build();
        when(mockExerciseRepository.save(any(Exercise.class))).thenReturn(exercise);
        Exercise returnExercise = exerciseService.persistExercise(exercise);
        verify(mockExerciseRepository, times(1)).save(any(Exercise.class));
        assertEquals(exercise, returnExercise);
    }

//    deleteexercise
    @Test
    public void deleteExercise() throws Exception {
        Exercise exercise = Exercise.builder().id(1L).name("testname").build();
        when(mockExerciseRepository.findById(anyLong())).thenReturn(Optional.ofNullable(exercise));
        boolean boolReturn = exerciseService.deleteCategory(1L);
        verify(mockExerciseRepository, times(1)).findById(anyLong());
        verify(mockExerciseRepository, times(1)).delete(any(Exercise.class));
        assertTrue(boolReturn);
    }
}
