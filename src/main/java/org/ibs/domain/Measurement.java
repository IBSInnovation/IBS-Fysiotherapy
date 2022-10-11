package org.ibs.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Measurement {
    private String id;
    private Date dateOfMeasurement;
    private ArrayList<Object> data;
    private Exercise exercise;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement that = (Measurement) o;
        return Objects.equals(id, that.id) && Objects.equals(dateOfMeasurement, that.dateOfMeasurement) && Objects.equals(data, that.data) && Objects.equals(exercise, that.exercise);
    }
}
