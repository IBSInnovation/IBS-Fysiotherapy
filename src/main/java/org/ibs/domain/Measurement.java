package org.ibs.domain;

import com.google.type.DateTime;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;
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


    private String euler_x;

    private String euler_y;

    private String euler_z;

    private String acc_x;

    private String acc_y;

    private String acc_z;

    private String batteryNumber;
    /*@JoinColumn()
    private ArrayList<Object> data;*/
    /*private Exercise exercise;*/

    public Measurement(Date dateOfMeasurement, String euler_x, String euler_y, String euler_z, String acc_x, String acc_y, String acc_z, String batteryNumber){
        this.dateOfMeasurement = dateOfMeasurement;
        this.euler_x = euler_x;
        this.euler_y = euler_y;
        this.euler_z = euler_z;
        this.acc_x = acc_x;
        this.acc_y = acc_y;
        this.acc_z = acc_z;
        this.batteryNumber = batteryNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement that = (Measurement) o;
        return Objects.equals(id, that.id) && Objects.equals(dateOfMeasurement, that.dateOfMeasurement);
    }
}
