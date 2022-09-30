package org.ibs.application.dto;

import lombok.Builder;
import org.ibs.utils.DTO;

import java.util.Date;
import java.util.List;

@Builder
public class PatientDTO extends DTO {
    public Long id;
    public String name;
    public String surName;
    public double weight;
    public Date dateOfBirth;
    public double height;
    public String email;
    public Long physiotherapistId;
    public List<Long> exerciseIds;
}
