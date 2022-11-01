package org.ibs.application.dto.measurementdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetMeasurement {
    public String id;
    public Date dateOfMeasurement;
    public ArrayList<Object> data;
    public String exerciseId;
}
