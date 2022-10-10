package org.ibs.application;

import org.ibs.application.dto.measurementdto.SaveMeasurement;
import org.ibs.application.dto.patientdto.GetPatient;
import org.ibs.application.dto.patientdto.SavePatient;

import java.util.List;

public interface IPatientService {
    GetPatient getById(String id) throws Exception;
    List<GetPatient> getAll() throws Exception;
    SavePatient savePatient(SavePatient patient) throws Exception;
    boolean deletePatient(String id) throws Exception;

    SaveMeasurement saveMeasurement(SaveMeasurement saveMeasurement) throws Exception;

    boolean deleteMeasurement(SaveMeasurement saveMeasurement) throws Exception;
}
