package org.ibs.application;

import org.ibs.application.dto.measurementdto.SaveMeasurement;
import org.ibs.domain.Measurement;

import java.util.List;

public interface IMeasurementService {
    Measurement getById(String id) throws Exception;
    List<Measurement> getAll() throws Exception;
    SaveMeasurement saveMeasurement(SaveMeasurement saveMeasurement) throws Exception;
    boolean deleteMeasurement(String id) throws Exception;
}
