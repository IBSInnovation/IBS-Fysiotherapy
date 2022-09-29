package org.ibs.application.dto;

import lombok.Builder;
import org.ibs.utils.DTO;

@Builder
public class PhysiotherapistDTO extends DTO {
    public Long id;
    public String email;
}
