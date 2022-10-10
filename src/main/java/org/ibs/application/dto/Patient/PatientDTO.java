package org.ibs.application.dto.Patient;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.ibs.utils.DTO;

import java.util.Date;

@Builder
@Getter
@Setter
public class PatientDTO extends DTO {
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
