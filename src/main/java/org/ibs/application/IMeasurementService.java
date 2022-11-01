package org.ibs.application;

import org.ibs.application.dto.measurementdto.GetMeasurement;
import org.ibs.application.dto.measurementdto.SaveMeasurement;
import org.ibs.application.dto.patientdto.SaveMeasurementPatient;

public interface IMeasurementService {
    GetMeasurement getMeasurementData(String id) throws Exception;

    GetMeasurement saveMeasurement(SaveMeasurement saveMeasurement) throws Exception;
    SaveMeasurementPatient saveMeasurementToPatient(SaveMeasurementPatient saveMeasurementPatient) throws Exception;

    GetMeasurement updateMeasurement(GetMeasurement getMeasurement) throws Exception;

    boolean deleteMeasurement(String id) throws Exception;
    boolean removeMeasurementFromPatient(String measurementId, String patientId) throws Exception;
}
