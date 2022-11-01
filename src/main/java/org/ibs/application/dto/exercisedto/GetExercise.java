package org.ibs.application.dto.exercisedto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
public class GetExercise {
    public String id;
    public String name;
    public ArrayList<String> description;
    public String categoryId;
}
