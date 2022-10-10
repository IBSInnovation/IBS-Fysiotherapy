package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.ICategoryService;
import org.ibs.application.dto.CategoryDTO;
import org.ibs.application.dto.mapper.CategoryDTOMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controller")
@AllArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;
    private final CategoryDTOMapper categoryDTOMapper;

//    fix de throw errors samen met morris

    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable long id) throws Exception {
        return categoryDTOMapper.toDTO(categoryService.getById(id));
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories() throws Exception {
        return categoryDTOMapper.toMultipleDTO(categoryService.getAll());
    }

    @PostMapping
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) throws Exception {
        return categoryDTOMapper.toDTO(categoryService.persistCategory(categoryDTOMapper.fromDTO(categoryDTO)));
    }

    @PatchMapping
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO) throws Exception {
        return categoryDTOMapper.toDTO(categoryService.persistCategory(categoryDTOMapper.fromDTO(categoryDTO)));
    }

    @DeleteMapping("/{id}")
    public boolean deleteCategory(@PathVariable long id) throws Exception {
        return categoryService.deleteCategory(id);
    }
}
