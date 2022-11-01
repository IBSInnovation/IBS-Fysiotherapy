package org.ibs.application.dto.exercisedto;

import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
public class GetExercise {
    public String id;
    public String name;
    public ArrayList<String> description;
}
