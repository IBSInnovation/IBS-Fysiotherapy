package org.ibs.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class Patient {
    private long id;
    private String name;
    private String surName;
    private double weight;
    private Date dateOfBirth;
    private double height;
    private String email;

    private Physiotherapist physiotherapist;
    @Builder.Default
    private List<Exercise> exercises = new ArrayList<>();
}
