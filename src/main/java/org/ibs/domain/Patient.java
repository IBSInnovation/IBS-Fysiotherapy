package org.ibs.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/*@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component*/
@Data
@NoArgsConstructor
@Component
@Entity
public class Patient {
    @Id
    @GeneratedValue
    @Column(name = "patientId")
    private Long id;
    @Column(name="patientName")
    private String name;
    @Column(name="surName")
    private String surName;
    @Column(name="weight")
    private double weight;
    @Column(name="dateOfBirth")
    private Date dateOfBirth;
    @Column(name="height")
    private double height;
    @Column(name="email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL)
    @Column(name="measurements")
    private List<Measurement> measurements = new ArrayList<>();
    @OneToMany(cascade= CascadeType.ALL)
    @Column(name="exercises")
    private List<Exercise> exercises = new ArrayList<>();

    public Patient(String name, String surName, double weight, double height, String email)
    {
        this.name = name;
        this.surName = surName;
        this.weight = weight;
        this.height = height;
        this.email = email;
    }

    public void addMeasurement(Measurement measurement){
        measurements.add(measurement);
    }
    public void deleteMeasurement(Measurement measurement){
        measurements.remove(measurement);
    }

    public void addExercise(Exercise exercise) {
        if (!exercises.contains(exercise)) exercises.add(exercise);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return id == patient.id && Double.compare(patient.weight, weight) == 0 && Double.compare(patient.height, height) == 0 && Objects.equals(name, patient.name) && Objects.equals(surName, patient.surName) && Objects.equals(dateOfBirth, patient.dateOfBirth) && Objects.equals(email, patient.email) && Objects.equals(exercises, patient.exercises);
    }
}
