package org.ibs.application;

import org.ibs.application.dto.MeasurementDTO;
import org.ibs.domain.Measurement;

import java.util.ArrayList;

public interface IMeasurementService {
    Measurement getById(long id);
    ArrayList<Measurement> getAll();
    Measurement persistMeasurement(MeasurementDTO measurementDTO);
    boolean deleteMeasurement(long id);
}
