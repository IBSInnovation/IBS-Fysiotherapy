package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.ICategoryService;
import org.ibs.application.dto.categorydto.GetCategory;
import org.ibs.application.dto.categorydto.SaveCategory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controller")
@AllArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping("/{id}")
    public GetCategory getCategoryById(@PathVariable String id) throws Exception {
        return categoryService.getById(id);
    }

    @GetMapping
    public List<GetCategory> getAllCategories() throws Exception {
        return categoryService.getAll();
    }

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
