package org.ibs.data;

import com.google.cloud.firestore.DocumentReference;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.ibs.application.dto.SavePatient;

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

    public static PersistPatient toPersistPatient(SavePatient dto) {
        return PersistPatient.builder()
                .id(dto.id)
                .name(dto.name)
                .surName(dto.surName)
                .weight(dto.weight)
                .dateOfBirth(dto.dateOfBirth)
                .height(dto.height)
                .email(dto.email)
                .physiotherapistId(dto.physiotherapistId)
                .build();
    }
}
