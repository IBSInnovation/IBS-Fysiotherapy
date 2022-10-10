package org.ibs.application.dto.Patient;

import lombok.Builder;
import org.ibs.utils.DTO;

import java.util.Date;

@Builder
public class SavePatient extends DTO {
    public String id;
    public String name;
    public String surName;
    public double weight;
    public Date dateOfBirth;
    public double height;
    public String email;
    public String physiotherapistId;

//    public DocumentReference physiotherapistReference;
//    public List<String> exerciseIds;
}
