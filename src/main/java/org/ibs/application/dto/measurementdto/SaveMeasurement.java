package org.ibs.application.dto.measurementdto;

import com.google.type.DateTime;
import org.ibs.utils.DTO;

import java.util.ArrayList;
import java.util.Date;

public class SaveMeasurement extends DTO {
    public String id;
    public String patientId;
    public DateTime dateOfMeasurement;
    public ArrayList<Object> data;
    public String exerciseId;
    public String categoryId;

}
