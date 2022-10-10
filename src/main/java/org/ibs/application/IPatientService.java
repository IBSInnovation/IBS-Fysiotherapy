package org.ibs.application;

import org.ibs.application.dto.Patient.SavePatient;
import org.ibs.domain.Patient;

import java.util.List;

public interface IPatientService {
    Patient getById(String id) throws Exception;
    List<Patient> getAll() throws Exception;
    SavePatient savePatient(SavePatient patient) throws Exception;
    boolean deletePatient(String id) throws Exception;
}
