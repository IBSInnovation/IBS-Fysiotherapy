package org.ibs.application.dto.patientdto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class GetPatient {
    public String id;
    public String name;
    public String surName;
    public double weight;
    public Date dateOfBirth;
    public String email;
    public String physio;
}
