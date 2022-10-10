package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.ICategoryService;
import org.ibs.application.dto.SaveCategory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controller")
@AllArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;

//    fix de throw errors samen met morris

//    @GetMapping("/{id}")
//    public CategoryDTO getCategoryById(@PathVariable long id) throws Exception {
//        return categoryDTOMapper.toDTO(categoryService.getById(id));
//    }
//
//    @GetMapping
//    public List<CategoryDTO> getAllCategories() throws Exception {
//        return categoryDTOMapper.toMultipleDTO(categoryService.getAll());
//    }

    @PostMapping
    public SaveCategory createCategory(@RequestBody SaveCategory saveCategory) throws Exception {
        return categoryService.saveCategory(saveCategory);
    }

    @PatchMapping
    public SaveCategory updateCategory(@RequestBody SaveCategory saveCategory) throws Exception {
        return categoryService.saveCategory(saveCategory);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCategory(@PathVariable String id) throws Exception {
        return categoryService.deleteCategory(id);
    }
}
