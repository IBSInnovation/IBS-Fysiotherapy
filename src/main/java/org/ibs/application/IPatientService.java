package org.ibs.application;

import org.ibs.application.dto.patientdto.GetPatient;
import org.ibs.application.dto.patientdto.GetPatientMeasurementData;
import org.ibs.application.dto.patientdto.SavePatient;
import org.ibs.application.dto.physiotherapistdto.SavePhysioPatient;

import java.util.List;

public interface IPatientService {
    GetPatient getPatientData(String id) throws Exception;
    List<GetPatientMeasurementData> getPatientMeasurementData(String id) throws Exception;
    GetPatient savePatient(SavePatient patient) throws Exception;
    SavePhysioPatient savePatientToPhysio(SavePhysioPatient savePhysioPatient) throws Exception;
    GetPatient updatePatient(GetPatient getPatient) throws Exception;
    SavePhysioPatient updatePatientToPhysio(SavePhysioPatient savePhysioPatient) throws Exception;
    boolean deletePatient(String patientId) throws Exception;
    boolean removePatientFromPhysio(String patientId, String physioId) throws Exception;
}
