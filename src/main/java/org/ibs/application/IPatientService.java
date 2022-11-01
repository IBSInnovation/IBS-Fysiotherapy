package org.ibs.application;

import org.ibs.application.dto.measurementdto.AskMeasurement;
import org.ibs.application.dto.measurementdto.GetMeasurement;
import org.ibs.application.dto.measurementdto.SaveMeasurement;
import org.ibs.application.dto.patientdto.GetPatient;
import org.ibs.application.dto.patientdto.SavePatient;

import java.util.List;

public interface IPatientService {
    GetPatient getById(String id) throws Exception;
    List<GetPatient> getAll() throws Exception;
    SavePatient savePatient(SavePatient patient) throws Exception;
    boolean deletePatient(String patientId, String physioId) throws Exception;
}
