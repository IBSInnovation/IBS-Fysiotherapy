package org.ibs.mqtthandler.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.security.SecureRandom;
import java.util.UUID;

@Data
@NoArgsConstructor
@Component
@Entity
public class Mqtt {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name="data")
    private String data;



    public Mqtt(String data) {
        this.data = data;
    }
}
