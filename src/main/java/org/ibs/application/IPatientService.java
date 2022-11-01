package org.ibs.application;

import org.ibs.application.dto.patientdto.GetPatient;
import org.ibs.application.dto.patientdto.SavePatient;

public interface IPatientService {
    GetPatient getPatientData(String id) throws Exception;
    SavePatient savePatient(SavePatient patient) throws Exception;
    boolean deletePatient(String patientId, String physioId) throws Exception;
}
