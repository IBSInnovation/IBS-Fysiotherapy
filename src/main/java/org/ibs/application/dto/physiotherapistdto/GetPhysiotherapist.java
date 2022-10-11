package org.ibs.application.dto.physiotherapistdto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.ibs.utils.DTO;

@NoArgsConstructor
@AllArgsConstructor
public class GetPhysiotherapist extends DTO {
    public String id;
    public String email;
}
