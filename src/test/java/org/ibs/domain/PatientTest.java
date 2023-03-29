package org.ibs.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {
    private Patient patient;
    private Measurement measurement;
    @BeforeEach
    public void init(){
        this.patient = new Patient();
        this.measurement = new Measurement();
    }

    @Test
    public void testIfMeasurementIsAddedToPatientListAfterCall(){
        this.patient.addMeasurement(measurement);
        assertTrue(patient.getMeasurements().contains(measurement));
    }

    @Test
    public void testIfMeasurementIsRemovedFromPatientAfterCall(){
        this.patient.addMeasurement(measurement);
        this.patient.deleteMeasurement(measurement);
        assertFalse(patient.getMeasurements().contains(measurement));
    }
}
