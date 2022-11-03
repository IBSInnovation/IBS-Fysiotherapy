package org.ibs.application.dto.joindto;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
public class ExerciseAndMeasurementData {
    public String measurementId;
    public Date dateOfMeasurement;
    public ArrayList<Object> data;
    public String exerciseId;
    public String name;
    public ArrayList<String> description;
    public String categoryId;
}
