package org.ibs.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Measurement {
    @Id
    @GeneratedValue
    private long id;
    @Temporal(TemporalType.DATE)
    private Date dateOfMeasurement;
    private int data;
    @ManyToOne
    private Exercise exercise;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Measurement)) return false;
        Measurement that = (Measurement) o;
        return data == that.data && Objects.equals(dateOfMeasurement, that.dateOfMeasurement) && Objects.equals(exercise, that.exercise);
    }
}
