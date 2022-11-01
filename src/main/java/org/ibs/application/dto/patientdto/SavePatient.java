package org.ibs.application.dto.patientdto;

import org.ibs.utils.DTO;

import java.util.Date;

public class SavePatient extends DTO {
    public String id;
    public String name;
    public String surName;
    public double weight;
    public Date dateOfBirth;
    public String email;
}
