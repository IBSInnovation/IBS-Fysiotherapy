package org.ibs.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.ibs.application.dto.categorydto.SaveCategory;

@Builder
@Getter
@Setter
public class PersistCategory {
    private boolean exist;

    public static PersistCategory toPersistCategory(SaveCategory dto) {
        return PersistCategory.builder()
                .exist(true)
                .build();
    }
}
