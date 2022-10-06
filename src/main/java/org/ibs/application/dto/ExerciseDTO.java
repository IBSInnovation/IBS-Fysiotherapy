package org.ibs.application.dto;

import lombok.Builder;
import org.ibs.utils.DTO;

import java.util.List;

@Builder
public class ExerciseDTO extends DTO {
    public String id;
    public String name;
    public String patientId;
    public List<String> measurementIds;
    public String categoryId;
}
