package org.ibs.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.ibs.application.dto.SaveExercise;

import java.util.List;

@Builder
@Getter
@Setter
public class PersistExercise {
    public String id;
    public String name;
    public String patientId;
    public List<String> measurementIds;
    public String categoryId;

    public static PersistExercise toPersistExercise(SaveExercise dto) {
        return PersistExercise.builder()
                .id(dto.id)
                .name(dto.name)
                .patientId(dto.patientId)
                .measurementIds(dto.measurementIds)
                .categoryId(dto.categoryId)
                .build();
    }
}
