package org.ibs.application.dto.Physiotherapist;

import lombok.Builder;
import org.ibs.utils.DTO;

@Builder
public class SavePhysiotherapist extends DTO {
    public String id;
    public String email;
}
