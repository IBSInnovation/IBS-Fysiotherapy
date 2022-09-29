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
public class Exercise {
    private String name;

    private Patient patient;
    @Builder.Default
    private List<Measurement> measurements = new ArrayList<>();
    private Category category;
}
