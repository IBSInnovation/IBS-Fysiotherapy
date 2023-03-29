package org.ibs.application.service;

import org.ibs.application.dto.measurementdto.AskMeasurement;
import org.ibs.application.dto.measurementdto.DeleteMeasurement;
import org.ibs.application.dto.measurementdto.GetMeasurement;
import org.ibs.application.dto.patientdto.GetPatient;
import org.ibs.application.dto.patientdto.SavePatient;
import org.ibs.data.MeasurementRepository;
import org.ibs.data.PatientRepository;
import org.ibs.domain.Measurement;
import org.ibs.domain.Patient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PatientService {

    private PatientRepository patientRepository;

    private MeasurementRepository measurementRepository;

    public PatientService(PatientRepository patientRepository, MeasurementRepository measurementRepository) {
        this.patientRepository = patientRepository;
        this.measurementRepository = measurementRepository;
    }

    /**
     * Searches the database for a Patient entity with the given id and returns it if it exists.
     *
     * @param id
     * @return Patient of given id
     * @throws Exception
     */

    public GetPatient getById(String id) throws Exception {
        try {
            Patient patient = patientRepository.getReferenceById(Long.parseLong(id));
            return new GetPatient(patient.getName());
//            TODO add costum errors
        } catch (Exception e) {
            throw new Exception("Patient could not be found due to an error", e);
        }
    }

    /**
     * Searches the database for all Patient entities and returns them.
     *
     * @return List of Patient entities
     * @throws Exception
     */

    public List<GetPatient> getAll() throws Exception {
        try {
            List<Patient> patients = patientRepository.findAll();
            List<GetPatient> patientList = new ArrayList<>();
            for(Patient patient : patients){
                patientList.add(new GetPatient(patient.getName()));
            }
            return patientList;
        } catch (Exception e) {
            throw new Exception("Patients could not be found due to an error", e);
        }
    }

    /**
     * Saves and updates the given Patient entity in the database
     *
     * @param savePatient
     * @return THe saved Patient entity
     * @throws Exception
     */
    public SavePatient savePatient(SavePatient savePatient) throws Exception {
        try {
            Patient patient = new Patient(savePatient.name, savePatient.surName, savePatient.weight, savePatient.height, savePatient.email);
            patientRepository.save(patient);

            return new SavePatient(patient);
        } catch (Exception e) {
            throw new Exception("Patient was not persisted due to an error", e);
        }
    }

    public Patient updatePatient(String id, SavePatient savePatient) throws Exception {
        try {
            Patient patient = patientRepository.findById(Long.parseLong(id)).get();
            patient.setName(savePatient.name);
            patientRepository.save(patient);
            return patient;
        } catch (Exception e) {
            throw new Exception("Patient was not persisted due to an error", e);
        }
    }

    /**
     * Deletes the Patient entity with the given id.
     *
     * @param id
     * @return true if the operation succeeded
     * @throws Exception
     */

    public boolean deletePatient(String id) throws Exception {
        try {
            Patient patient = patientRepository.getById(Long.parseLong(id));
            patientRepository.delete(patient);
            return true;
        } catch (Exception e) {
            throw new Exception("Patient could not be deleted due to an error", e);
        }
    }


    public List<Measurement> saveMeasurement(String patientId) throws Exception {
        try {
            Patient patient = patientRepository.getById(Long.parseLong(patientId));
            Date date = new Date();
            patient.addMeasurement(new Measurement(date));
            patientRepository.save(patient);
            return patient.getMeasurements();
        } catch (Exception e) {
            throw new Exception("Measurement was not persisted due to an error", e);
        }
    }

    /**
     * Deletes the Measurement entity with the given id.
     * @param saveMeasurement
     * @return true if the operation succeeded
     * @throws Exception
     */

    public boolean deleteMeasurement(Long id, DeleteMeasurement deleteMeasurement) throws Exception {
        try {
            Patient patient = patientRepository.getById(id);
            Measurement measurement = measurementRepository.getById(Long.valueOf(deleteMeasurement.id));
            patient.deleteMeasurement(measurement);
            patientRepository.save(patient);
            return true;
        } catch (Exception e) {
            throw new Exception("Measurement could not be deleted due to an error", e);
        }
    }

    /**
     * Searches the database for all Measurement entities and returns them.
     * @return List of Measurement entities
     * @throws Exception
     */

    public GetMeasurement getAllMeasurements(AskMeasurement askMeasurement) throws Exception {
        /*try {
            List<>

            DocumentReference documentReference = db.collection("patient").document(askMeasurement.patientId)
                    .collection("category").document("doc")
                    .collection(askMeasurement.categoryId).document(askMeasurement.exerciseId);

            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            GetMeasurement getMeasurement;
            if (document.exists()) {
                getMeasurement = document.toObject(GetMeasurement.class);
                if (getMeasurement != null) {
                    return getMeasurement;
                } else {
                    throw new Exception("Measurement is null");
                }

            } else {
                throw new Exception("Document does not exist");
            }

        } catch (Exception e) {
            throw new Exception("Measurements could not be found due to an error", e);
        }*/
        return new GetMeasurement();
    }

}
