package org.ibs.application;

import org.ibs.application.service.CategoryService;
import org.ibs.data.CategoryRepository;
import org.ibs.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {
    CategoryService categoryService;
    CategoryRepository mockCategoryRepository;

    @BeforeEach
    public void init() {
        mockCategoryRepository = mock(CategoryRepository.class);
        categoryService = new CategoryService(mockCategoryRepository);
    }

//    getbyid
    @Test
    public void getByIdExistingCategory() throws Exception {
        Category category = Category.builder().id(1L).name("testname").build();
        when(mockCategoryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(category));
        Category cat = categoryService.getById(1L);
        verify(mockCategoryRepository, times(1)).findById(anyLong());
        assertEquals(1L, cat.getId());
        assertEquals("testname", cat.getName());
    }

    @Test
    public void getByIdNonExistingCategory() {
        assertThrows(Exception.class, () -> categoryService.getById(1L));
    }

//    getall
    @Test
    public void getAllExisting() throws Exception {
        Category category1 = Category.builder().id(1L).name("testname1").build();
        Category category2 = Category.builder().id(2L).name("testname2").build();
        Category category3 = Category.builder().id(3L).name("testname3").build();
        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);
        when(mockCategoryRepository.findAll()).thenReturn(categories);
        assertEquals(categories, categoryService.getAll());
        verify(mockCategoryRepository, times(1)).findAll();
    }

//    persistcategory
    @Test
    public void persistCategory() throws Exception {
        Category category = Category.builder().id(1L).name("testname").build();
        when(mockCategoryRepository.save(any(Category.class))).thenReturn(category);
        Category returnedCategory = categoryService.persistCategory(category);
        verify(mockCategoryRepository, times(1)).save(any(Category.class));
        assertEquals(category, returnedCategory);
    }

//    deletecategory
    @Test
    public void deleteCategory() throws Exception {
        Category category = Category.builder().id(1L).name("testname").build();
        when(mockCategoryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(category));
        boolean boolReturn = categoryService.deleteCategory(1L);
        verify(mockCategoryRepository, times(1)).findById(anyLong());
        verify(mockCategoryRepository, times(1)).delete(any(Category.class));
        assertTrue(boolReturn);
    }
}
