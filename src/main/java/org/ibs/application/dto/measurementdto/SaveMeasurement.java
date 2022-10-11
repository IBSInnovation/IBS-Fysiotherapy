package org.ibs.application.dto.measurementdto;

import lombok.Builder;
import org.ibs.utils.DTO;

import java.util.ArrayList;
import java.util.Date;

@Builder
public class SaveMeasurement extends DTO {
    public String id;
    public String patientId;
    public Date dateOfMeasurement;
    public ArrayList<Object> data;
    public String exerciseId;
    public String categoryId;

}