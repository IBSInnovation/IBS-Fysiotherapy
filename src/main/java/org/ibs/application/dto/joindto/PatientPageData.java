package org.ibs.application.dto.joindto;

import lombok.AllArgsConstructor;
import org.ibs.application.dto.patientdto.GetPatientMeasurementData;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class PatientPageData {
    public String id;
    public String name;
    public String surName;
    public double weight;
    public Date dateOfBirth;
    public String email;
    public List<GetPatientMeasurementData> patientMeasurementData;
}
