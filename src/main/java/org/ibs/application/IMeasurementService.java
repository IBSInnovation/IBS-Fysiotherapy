package org.ibs.application;

import org.ibs.application.dto.MeasurementDTO;
import org.ibs.domain.Measurement;

import java.util.List;

public interface IMeasurementService {
    Measurement getById(long id);
    List<Measurement> getAll();
    Measurement persistMeasurement(MeasurementDTO measurementDTO);
    boolean deleteMeasurement(long id);
}
