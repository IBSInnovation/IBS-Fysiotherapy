package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.ibs.application.IPatientService;
import org.ibs.application.dto.measurementdto.AskMeasurement;
import org.ibs.application.dto.measurementdto.GetMeasurement;
import org.ibs.application.dto.measurementdto.SaveMeasurement;
import org.ibs.application.dto.patientdto.GetPatient;
import org.ibs.application.dto.patientdto.SavePatient;
import org.ibs.data.PatientRepository;
import org.ibs.data.PersistMeasurement;
import org.ibs.data.PersistPatient;
import org.ibs.domain.Measurement;
import org.ibs.domain.Patient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PatientService implements IPatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Searches the database for a Patient entity with the given id and returns it if it exists.
     *
     * @param id
     * @return Patient of given id
     * @throws Exception
     */
    @Override
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
    @Override
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
    @Override
    public SavePatient savePatient(SavePatient savePatient) throws Exception {
        try {
            Patient patient = new Patient(savePatient.name);
            patientRepository.save(patient);

            return new SavePatient();
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
    @Override
    public boolean deletePatient(String id) throws Exception {
        try {
            Patient patient = patientRepository.getById(Long.parseLong(id));
            patientRepository.delete(patient);
            return true;
        } catch (Exception e) {
            throw new Exception("Patient could not be deleted due to an error", e);
        }
    }

    @Override
    public SaveMeasurement saveMeasurement(SaveMeasurement saveMeasurement) throws Exception {
        try {
            Patient patient = patientRepository.getById(Long.parseLong(saveMeasurement.patientId));
            patient.addMeasurement(new Measurement(saveMeasurement.dateOfMeasurement));
            patientRepository.save(patient);
            return saveMeasurement;
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
    @Override
    public boolean deleteMeasurement(SaveMeasurement saveMeasurement) throws Exception {
        try {
            Patient patient = patientRepository.getById(Long.parseLong(saveMeasurement.patientId));
            patient.deleteMeasurement(new Measurement(saveMeasurement.dateOfMeasurement));
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
    @Override
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
