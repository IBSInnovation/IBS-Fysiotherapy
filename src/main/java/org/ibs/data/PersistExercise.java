package org.ibs.data;

import com.google.firebase.database.Exclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.ibs.application.dto.SaveExercise;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
public class PersistExercise {
    public String id;
    public ArrayList<String> name;

    public static PersistExercise toPersistExercise(SaveExercise dto) {
        return PersistExercise.builder()
                .id(dto.id)
                .name(dto.name)
                .build();
    }
}
