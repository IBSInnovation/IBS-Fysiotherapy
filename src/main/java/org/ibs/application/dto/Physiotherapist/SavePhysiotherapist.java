package org.ibs.application.dto.Physiotherapist;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.ibs.utils.DTO;

@Builder
@Getter
@Setter
public class SavePhysiotherapist extends DTO {
    public String id;
    public String email;
}
