package org.ibs.application;

import org.ibs.application.dto.patientdto.GetPatient;
import org.ibs.application.dto.patientdto.GetPatientMeasurementData;
import org.ibs.application.dto.patientdto.SavePatient;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IPatientService {
    GetPatient getPatientData(String id) throws Exception;

    List<GetPatientMeasurementData> getPatientMeasurementData(String id) throws ExecutionException, InterruptedException;

    SavePatient savePatient(SavePatient patient) throws Exception;

    //    update patient
    GetPatient updatePatient(GetPatient getPatient) throws Exception;

    boolean deletePatient(String patientId, String physioId) throws Exception;
}
