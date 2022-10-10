package org.ibs.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.ibs.application.dto.Physiotherapist.SavePhysiotherapist;

@Builder
@Getter
@Setter
public class PersistPhysiotherapist {
    private String id;
    private String email;

    public static PersistPhysiotherapist toPersistPhysio(SavePhysiotherapist dto) {
        return PersistPhysiotherapist.builder()
                .id(dto.id)
                .email(dto.email)
                .build();
    }
}
