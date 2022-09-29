package org.ibs.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient {
    private long id;
    private String name;
    private String surName;
    private double weight;
    private Date dateOfBirth;
    private double height;
    private String email;

    private Physiotherapist physiotherapist;
    private List<Exercise> exercises = new ArrayList<>();
}
