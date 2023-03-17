package org.ibs.application.dto.categorydto;

import org.ibs.domain.Category;
import org.ibs.presentation.CategoryController;
import org.ibs.utils.DTO;

public class GetCategory extends DTO {
    public String id;
    public String name;
    public GetCategory(Category category){
        this.id = category.getId();
        this.name = category.getName();
    }
}
