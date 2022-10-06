package org.ibs.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
    private String id;
    private String name;
    private Patient patient;
    @Builder.Default
    private List<Measurement> measurements = new ArrayList<>();
    private Category category;

    public void addMeasurement(Measurement measurement) {
        if (!measurements.contains(measurement)) measurements.add(measurement);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exercise)) return false;
        Exercise exercise = (Exercise) o;
        return Objects.equals(name, exercise.name) && Objects.equals(patient, exercise.patient) && Objects.equals(measurements, exercise.measurements) && Objects.equals(category, exercise.category);
    }

}
