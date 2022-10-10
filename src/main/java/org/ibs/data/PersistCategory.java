package org.ibs.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.ibs.application.dto.SaveCategory;

@Builder
@Getter
@Setter
public class PersistCategory {

    public static PersistCategory toPersistCategory(SaveCategory dto) {
        return PersistCategory.builder()
                .build();
    }
}
