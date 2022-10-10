package org.ibs.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.ibs.application.dto.SaveCategory;

@Builder
@Getter
@Setter
public class PersistCategory {
    private String id;
    private String name;

    public static PersistCategory toPersistCategory(SaveCategory dto) {
        return PersistCategory.builder()
                .id(dto.id)
                .name(dto.name)
                .build();
    }
}
