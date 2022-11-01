package org.ibs.application.dto.measurementdto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class GetMeasurement {
    public String id;
    public Date dateOfMeasurement;
    public ArrayList<Object> data;
    public String exercise;
}
