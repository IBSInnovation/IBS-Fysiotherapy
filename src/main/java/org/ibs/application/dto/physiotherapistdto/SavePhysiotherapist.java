package org.ibs.application.dto.physiotherapistdto;

import lombok.Builder;
import org.ibs.utils.DTO;

@Builder
public class SavePhysiotherapist extends DTO {
    public String id;
    public String email;
}
