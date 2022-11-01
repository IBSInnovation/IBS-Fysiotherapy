package org.ibs.application;

import org.ibs.application.dto.measurementdto.AskMeasurement;
import org.ibs.application.dto.measurementdto.GetMeasurement;
import org.ibs.application.dto.measurementdto.SaveMeasurement;

public interface IMeasurementService {
    GetMeasurement saveMeasurement(SaveMeasurement saveMeasurement) throws Exception;

    boolean deleteMeasurement(SaveMeasurement saveMeasurement) throws Exception;
}
