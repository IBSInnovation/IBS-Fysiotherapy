package org.ibs.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.ibs.application.dto.exercisedto.SaveExercise;

import java.util.ArrayList;

@Builder
@Getter
@Setter
public class PersistExercise {
    public String id;
    public String name;
    public ArrayList<String> description;

    public static PersistExercise toPersistExercise(SaveExercise dto) {
        return PersistExercise.builder()
                .id(dto.id)
                .name(dto.name)
                .description(dto.description)
                .build();
    }
}
