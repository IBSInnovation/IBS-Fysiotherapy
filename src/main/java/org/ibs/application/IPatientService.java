package org.ibs.application;

import org.ibs.domain.Patient;

import java.util.List;

public interface IPatientService {
    Patient getById(long id) throws Exception;
    List<Patient> getAll() throws Exception;
    Patient persistPatient(Patient patient) throws Exception;
    boolean deletePatient(long id) throws Exception;
}
