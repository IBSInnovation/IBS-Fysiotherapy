package org.ibs.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
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
public class Physiotherapist {
    @Id
    @GeneratedValue
    @Column(name = "physiotherapistId")
    private Long id;
    @Column(name="email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL)
    @Column(name= "patients")
    private List<Patient> patients = new ArrayList<>();

    public Physiotherapist(String email){
        this.email = email;
    }

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
