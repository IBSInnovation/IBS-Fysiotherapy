package org.ibs.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String surName;
    private double weight;
    private Date dateOfBirth;
    private double height;
    private String email;

    @ManyToOne
    private Physiotherapist physiotherapist;
    @Builder.Default
    @OneToMany(mappedBy = "patient")
    private List<Exercise> exercises = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return id == patient.id && Double.compare(patient.weight, weight) == 0 && Double.compare(patient.height, height) == 0 && Objects.equals(name, patient.name) && Objects.equals(surName, patient.surName) && Objects.equals(dateOfBirth, patient.dateOfBirth) && Objects.equals(email, patient.email) && Objects.equals(physiotherapist, patient.physiotherapist) && Objects.equals(exercises, patient.exercises);
    }
}
