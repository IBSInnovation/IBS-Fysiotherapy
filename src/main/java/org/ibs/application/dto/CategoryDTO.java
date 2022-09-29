package org.ibs.application.dto;

import lombok.Builder;
import org.ibs.utils.DTO;

import java.util.ArrayList;

@Builder
public class CategoryDTO extends DTO {
    public Long id;
    public String name;
    public ArrayList<Long> exerciseIds;
}
