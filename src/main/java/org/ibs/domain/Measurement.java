package org.ibs.domain;

import com.google.type.DateTime;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/*
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
*/

@Data
@NoArgsConstructor
@Component
@Entity
public class Measurement {
    @Id
    @GeneratedValue
    @Column(name = "measurementId")
    private Long id;
    @Column(name="dateOfMeasurement")
    private Date dateOfMeasurement;

    /*@JoinColumn()
    private ArrayList<Object> data;*/
    /*private Exercise exercise;*/

    public Measurement(Date dateOfMeasurement){
        this.dateOfMeasurement = dateOfMeasurement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement that = (Measurement) o;
        return Objects.equals(id, that.id) && Objects.equals(dateOfMeasurement, that.dateOfMeasurement);
    }
}
