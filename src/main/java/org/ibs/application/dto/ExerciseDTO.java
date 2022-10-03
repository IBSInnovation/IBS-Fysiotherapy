package org.ibs.application.dto;

import lombok.Builder;
import org.ibs.utils.DTO;

import java.util.List;

@Builder
public class ExerciseDTO extends DTO {
    public Long id;
    public String name;
    public Long patientId;
    public List<Long> measurementIds;
    public Long categoryId;
}
