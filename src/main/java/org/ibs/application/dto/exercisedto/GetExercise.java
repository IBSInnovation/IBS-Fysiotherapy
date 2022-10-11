package org.ibs.application.dto.exercisedto;

import lombok.NoArgsConstructor;
import org.ibs.utils.DTO;

import java.util.ArrayList;

@NoArgsConstructor
public class GetExercise extends DTO {
    public String id;
    public String name;
    public ArrayList<String> description;
}
