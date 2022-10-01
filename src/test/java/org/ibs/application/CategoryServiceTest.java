package org.ibs.application;

import org.ibs.application.service.CategoryService;
import org.ibs.data.CategoryRepository;
import org.ibs.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        when(mockCategoryRepository.findById(1L)).thenReturn(Optional.ofNullable(category));
        Category cat = categoryService.getById(1L);
        assertEquals(1L, cat.getId());
        assertEquals("testname", cat.getName());
    }

    @Test
    public void getByIdNonExistingCategory() {
        assertThrows(Exception.class, () -> categoryService.getById(1L));
    }

//    getall

//    persistcategory

//    deletecategory
}
