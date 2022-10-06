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
public class Physiotherapist {
    private String id;
    private String email;
    @Builder.Default
    private List<Patient> patients = new ArrayList<>();

    public void addPatient(Patient patient) {
        if (!patients.contains(patient)) patients.add(patient);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Physiotherapist)) return false;
        Physiotherapist that = (Physiotherapist) o;
        return Objects.equals(email, that.email) && Objects.equals(patients, that.patients);
    }
}
