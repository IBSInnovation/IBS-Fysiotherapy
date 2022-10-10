package org.ibs.application.dto.exercisedto;

import lombok.Builder;
import org.ibs.utils.DTO;

@Builder
public class GetExercise extends DTO {
    public String id;
    public String name;
}
