package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.dto.categorydto.GetCategory;
import org.ibs.application.dto.categorydto.SaveCategory;
import org.ibs.application.service.CategoryService;
import org.ibs.domain.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public GetCategory getCategoryById(@PathVariable String id) throws Exception {
        return categoryService.getById(id);
    }

    @GetMapping
    public List<GetCategory> getAllCategories() throws Exception {
        return categoryService.getAll();
    }

    @PostMapping
    public Category createCategory(@RequestBody SaveCategory saveCategory) throws Exception {
        return categoryService.saveCategory(saveCategory);
    }

    @PatchMapping("/{id}")
    public Category updateCategory(@PathVariable String id, @RequestBody SaveCategory saveCategory) throws Exception{
        return categoryService.updateCategory(id, saveCategory);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCategory(@PathVariable String id) throws Exception {
        return categoryService.deleteCategory(id);
    }
}
