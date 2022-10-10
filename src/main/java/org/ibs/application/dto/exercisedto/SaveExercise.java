package org.ibs.application.dto.exercisedto;

import lombok.Builder;
import org.ibs.utils.DTO;

import java.util.ArrayList;
import java.util.List;

@Builder
public class SaveExercise extends DTO {
    public String id;
    public ArrayList<String> name;
    public String categoryId;
}
