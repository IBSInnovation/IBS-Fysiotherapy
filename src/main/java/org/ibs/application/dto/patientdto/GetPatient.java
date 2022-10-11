package org.ibs.application.dto.patientdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.ibs.utils.DTO;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPatient extends DTO {
    public String id;
    public String name;
    public String surName;
    public double weight;
    public Date dateOfBirth;
    public double height;
    public String email;
    public String physiotherapistId;

}
