package org.ibs.application.dto.Patient;

import lombok.Builder;
import org.ibs.utils.DTO;

import java.util.Date;
import java.util.List;

@Builder
public class PatientDTO extends DTO {
    public String id;
    public String name;
    public String surName;
    public double weight;
    public Date dateOfBirth;
    public double height;
    public String email;
    public String physiotherapistId;

//    public List<String> exerciseIds;
}
