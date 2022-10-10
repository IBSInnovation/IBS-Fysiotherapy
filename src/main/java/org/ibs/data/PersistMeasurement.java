package org.ibs.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.ibs.application.dto.measurementdto.SaveMeasurement;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@Builder
@Getter
@Setter
public class PersistMeasurement {
    private ArrayList<Object> data;

    public static PersistMeasurement toPersistMeasurement(SaveMeasurement dto) {
        return PersistMeasurement.builder()
                .data(dto.data)
                .build();
    }
}
