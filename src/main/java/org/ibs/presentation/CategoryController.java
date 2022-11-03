package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.ICategoryService;
import org.ibs.application.IJoinService;
import org.ibs.application.dto.PlaceholderDTO;
import org.ibs.application.dto.categorydto.GetCategory;
import org.ibs.application.dto.categorydto.SaveCategory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;
    private final IJoinService joinService;

//    TODO: zal niet meer gebruikt worden
    @GetMapping("/{id}")
    public GetCategory getCategoryById(@PathVariable String id) throws Exception {
        return categoryService.getCategoryData(id);
    }

//    TODO: verander de return waarde naar de juiste attributen
    @GetMapping
    public List<GetCategory> getAllCategories() throws Exception {
        return categoryService.getAll();
    }

    @PostMapping
    public GetCategory createCategory(@RequestBody SaveCategory saveCategory) throws Exception {
        return categoryService.saveCategory(saveCategory);
    }

//    TODO: kan verwijderd worden. alle updates worden eigenlijk op de exercises gedaan, dus dat kan geregeld worden in de service laag
    @PatchMapping
    public PlaceholderDTO updateCategory(@RequestBody GetCategory getCategory) {
        return joinService.updateCategory(getCategory);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCategory(@PathVariable String id) {
        return joinService.deleteCategoryAndSubcollections(id);
    }
}
