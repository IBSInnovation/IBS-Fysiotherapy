package org.ibs.application.dto;

import lombok.Builder;
import org.ibs.utils.DTO;

import java.util.List;

@Builder
public class PhysiotherapistDTO extends DTO {
    public String id;
    public String email;
    public List<String> patients;
}
