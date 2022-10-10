package org.ibs.data;

import com.google.cloud.firestore.DocumentReference;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.ibs.application.dto.Patient.PatientDTO;

import java.util.Date;

@Builder
@Getter
@Setter
public class PersistPatient {
    private String id;
    private String name;
    private String surName;
    private double weight;
    private Date dateOfBirth;
    private double height;
    private String email;
    private String physiotherapistId;
    private DocumentReference physiotherapistReference;

    public static PersistPatient toPersistPatient(PatientDTO dto) {
        return PersistPatient.builder()
                .id(dto.getId())
                .name(dto.getName())
                .surName(dto.getSurName())
                .weight(dto.getWeight())
                .dateOfBirth(dto.getDateOfBirth())
                .height(dto.getHeight())
                .email(dto.getEmail())
                .physiotherapistId(dto.getPhysiotherapistId())
                .build();
    }
}
