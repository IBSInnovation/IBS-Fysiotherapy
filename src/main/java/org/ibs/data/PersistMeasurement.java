package org.ibs.data;

import com.google.cloud.firestore.DocumentReference;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.ibs.application.dto.SaveMeasurement;

import java.util.Date;

@Builder
@Getter
@Setter
public class PersistMeasurement {
    private String id;
    private Date dateOfMeasurement;
    private int data;
    private String exerciseId;

    public static PersistMeasurement toPersistMeasurement(SaveMeasurement dto) {
        return PersistMeasurement.builder()
                .id(dto.id)
                .dateOfMeasurement(dto.dateOfMeasurement)
                .data(dto.data)
                .exerciseId(dto.exerciseId)
                .build();
    }
}
