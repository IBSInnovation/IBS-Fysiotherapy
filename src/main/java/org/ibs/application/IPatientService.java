package org.ibs.application;

import org.ibs.application.dto.PatientDTO;
import org.ibs.domain.Patient;

import java.util.List;

public interface IPatientService {
    Patient getById(long id);
    List<Patient> getAll();
    Patient persistPatient(PatientDTO patientDTO);
    boolean deletePatient(long id);
}
