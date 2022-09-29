package org.ibs.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
public class Measurement {
    private Date dateOfMeasurement;
    private int data;

    private Exercise exercise;
}
