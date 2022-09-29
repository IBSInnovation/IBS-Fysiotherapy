package org.ibs.application.dto;

import lombok.Builder;
import org.ibs.utils.DTO;

import java.util.ArrayList;
import java.util.Date;

@Builder
public class PatientDTO extends DTO {
    public Long id;
    public String name;
    public String surName;
    public double weight;
    public Date dateOfBirth;
    public double height;
    public String email;
    public ArrayList<Long> exerciseIds;
}
