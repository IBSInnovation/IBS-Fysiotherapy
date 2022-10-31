package org.ibs.application.dto.physiotherapistdto;

import lombok.NoArgsConstructor;
import org.ibs.utils.DTO;

@NoArgsConstructor
public class GetPhysiotherapist extends DTO {
    public String id;
    public String email;
    public String name;
}
