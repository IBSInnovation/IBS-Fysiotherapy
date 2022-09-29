package org.ibs.domain;

import java.util.ArrayList;
import java.util.List;

public class Exercise {
    private String name;

    private Patient patient;
    private List<Measurement> measurements = new ArrayList<>();
    private Category category;
}
