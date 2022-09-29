package org.ibs.application.service;

import lombok.AllArgsConstructor;
import org.ibs.application.IPatientService;
import org.ibs.application.dto.PatientDTO;
import org.ibs.domain.Patient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
@AllArgsConstructor
public class PatientService implements IPatientService {
    @Override
    public Patient getById(long id) {
        return null;
    }

    @Override
    public ArrayList<Patient> getAll() {
        return null;
    }

    @Override
    public Patient persistPatient(PatientDTO patientDTO) {
        return null;
    }

    @Override
    public boolean deletePatient(long id) {
        return false;
    }
}
