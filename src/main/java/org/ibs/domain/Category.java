package org.ibs.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
    public class Category {
    private String name;

    @Builder.Default
    private List<Exercise> exercises = new ArrayList<>();


}
