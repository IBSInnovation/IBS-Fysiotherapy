package org.ibs.application.dto.categorydto;

import lombok.Builder;
import org.ibs.utils.DTO;

@Builder
public class SaveCategory extends DTO {
    public String id;
    public String name;

//    public List<String> exerciseIds;
}
