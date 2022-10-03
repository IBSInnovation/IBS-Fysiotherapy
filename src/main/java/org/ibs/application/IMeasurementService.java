package org.ibs.application;

import org.ibs.domain.Measurement;

import java.util.List;

public interface IMeasurementService {
    Measurement getById(long id) throws Exception;
    List<Measurement> getAll() throws Exception;
    Measurement persistMeasurement(Measurement measurement) throws Exception;
    boolean deleteMeasurement(long id) throws Exception;
}
