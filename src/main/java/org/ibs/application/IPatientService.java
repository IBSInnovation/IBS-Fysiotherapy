package org.ibs.application;

import org.ibs.application.dto.PatientDTO;
import org.ibs.domain.Patient;

import java.util.ArrayList;

public interface IPatientService {
    Patient getById(long id);
    ArrayList<Patient> getAll();
    Patient persistPatient(PatientDTO patientDTO);
    boolean deletePatient(long id);
}
