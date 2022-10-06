package org.ibs.application.dto.Physiotherapist;

import lombok.Builder;
import org.ibs.utils.DTO;

import java.util.List;

@Builder
public class PhysiotherapistDTO extends DTO {
    public String id;
    public String email;

}
