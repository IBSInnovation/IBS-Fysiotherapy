package org.ibs.application.service;

import lombok.AllArgsConstructor;
import org.ibs.application.IPatientService;
import org.ibs.data.PatientRepository;
import org.ibs.domain.Patient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PatientService implements IPatientService {
    private final PatientRepository patientRepository;

    /**
     * Searches the database for a Patient entity with the given id and returns it if it exists.
     * @param id
     * @return Patient of given id
     * @throws Exception
     */
    @Override
    public Patient getById(long id) throws Exception {
        try {
            return patientRepository.findById(id).orElseThrow(Exception::new);
        } catch (Exception e) {
            throw new Exception("Patient could not be found due to an error", e);
        }
    }

    /**
     * Searches the database for all Patient entities and returns them.
     * @return List of Patient entities
     * @throws Exception
     */
    @Override
    public List<Patient> getAll() throws Exception {
        try {
            return patientRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Patients could not be found due to an error", e);
        }
    }

    /**
     * Saves and updates the given Patient entity in the database
     * @param patient
     * @return THe saved Patient entity
     * @throws Exception
     */
    @Override
    public Patient persistPatient(Patient patient) throws Exception {
        try {
            return patientRepository.save(patient);
        } catch (Exception e) {
            throw new Exception("Patient was not eprsisted due to an error", e);
        }
    }

    /**
     * Deletes the Patient entity with the given id.
     * @param id
     * @return true if the operation succeeded
     * @throws Exception
     */
    @Override
    public boolean deletePatient(long id) throws Exception {
        try {
            patientRepository.delete(patientRepository.findById(id).orElseThrow(Exception::new));
            return true;
        } catch (Exception e) {
            throw new Exception("Patient could not be deleted due to an error", e);
        }
    }
}
