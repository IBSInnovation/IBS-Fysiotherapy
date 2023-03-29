package org.ibs.application.dto.patientdto;

import org.ibs.domain.Patient;
import org.ibs.utils.DTO;

import java.util.Date;

public class SavePatient extends DTO {
    public String name;
    public String surName;
    public double weight;
    public Date dateOfBirth;
    public double height;
    public String email;
    public String physiotherapistId;

    public SavePatient() {
    }

    public SavePatient(Patient patient) {
        this.name = patient.getName();
        this.surName = patient.getSurName();
        this.weight = patient.getWeight();
        this.height = patient.getHeight();
        this.email = patient.getEmail();
    }
}
