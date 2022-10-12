package org.ibs.application.dto.exercisedto;

import org.ibs.utils.DTO;

import java.util.ArrayList;

public class SaveExercise extends DTO {
    public String id;
    public String name;
    public ArrayList<String> description;
    public String categoryId;
}
