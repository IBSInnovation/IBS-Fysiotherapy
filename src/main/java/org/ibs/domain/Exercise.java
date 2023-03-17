package org.ibs.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
public class Exercise {
    @Id
    @GeneratedValue
    @Column(name = "exerciseId")
    private String id;
    @Column(name = "exerciseName")
    private String name;
    /*@Column(name = "patient")
    private Patient patient;*/
    @OneToMany(cascade = CascadeType.ALL)
    @Column(name="measurements")
    private List<Measurement> measurements = new ArrayList<>();
    /*@ManyToOne
    private Category category;*/

    public Exercise(String name) {
        this.name = name;
    }


    public void addMeasurement(Measurement measurement) {
        if (!measurements.contains(measurement)) measurements.add(measurement);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exercise)) return false;
        Exercise exercise = (Exercise) o;
        return Objects.equals(name, exercise.name) && Objects.equals(measurements, exercise.measurements);
    }

}
