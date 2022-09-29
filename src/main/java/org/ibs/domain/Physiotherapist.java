package org.ibs.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Physiotherapist {
    @Id
    @GeneratedValue
    private long id;
    private String email;

    @Builder.Default
    @OneToMany(mappedBy = "physiotherapist")
    private List<Patient> patients = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Physiotherapist)) return false;
        Physiotherapist that = (Physiotherapist) o;
        return Objects.equals(email, that.email) && Objects.equals(patients, that.patients);
    }
}
