package org.ibs.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.ibs.application.dto.measurementdto.SaveMeasurement;

import java.util.ArrayList;
import java.util.Date;

@Builder
@Getter
@Setter
public class PersistMeasurement {
    private String id;
    private Date dateOfMeasurement;
    private ArrayList<Object> data;

    public static PersistMeasurement toPersistMeasurement(SaveMeasurement dto) {
        return PersistMeasurement.builder()
                .id(dto.id)
                .dateOfMeasurement(dto.dateOfMeasurement)
                .data(dto.data)
                .build();
    }
}
