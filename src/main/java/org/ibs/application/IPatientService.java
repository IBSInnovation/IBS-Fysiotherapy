package org.ibs.application;

import org.ibs.application.dto.patientdto.GetPatient;
import org.ibs.application.dto.patientdto.GetPatientMeasurementData;
import org.ibs.application.dto.patientdto.SaveMeasurementPatient;
import org.ibs.application.dto.patientdto.SavePatient;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IPatientService {
    GetPatient getPatientData(String id) throws Exception;

    List<GetPatientMeasurementData> getPatientMeasurementData(String id) throws Exception;

    GetPatient savePatient(SavePatient patient) throws Exception;

    SaveMeasurementPatient saveMeasurementToPatient(SaveMeasurementPatient saveMeasurementPatient) throws Exception;

    GetPatient updatePatient(GetPatient getPatient) throws Exception;

    boolean deletePatient(String patientId) throws Exception;

    boolean removePatientFromPhysio(String patientId, String physioId) throws Exception;
}
