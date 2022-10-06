package org.ibs.application;

import org.ibs.application.dto.Patient.PatientDTO;
import org.ibs.domain.Patient;

import java.util.List;

public interface IPatientService {
    Patient getById(String id) throws Exception;
    List<Patient> getAll() throws Exception;
    PatientDTO savePatient(PatientDTO patient) throws Exception;
    boolean deletePatient(String id) throws Exception;
}
