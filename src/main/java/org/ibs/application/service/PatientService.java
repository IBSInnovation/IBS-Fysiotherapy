package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.AllArgsConstructor;
import org.ibs.application.IPatientService;
import org.ibs.data.PatientRepository;
import org.ibs.domain.Patient;
import org.ibs.domain.Physiotherapist;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    public Patient getById(String id) throws Exception {
        try {
            Firestore db = FirestoreClient.getFirestore();
            DocumentReference documentReference = db.collection("patients").document(id);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            Patient patient;
            if (document.exists()) {
                patient = document.toObject(Patient.class);
                return patient;
            }
        } catch (Exception e) {
            throw new Exception("Patient could not be found due to an error", e);
        }
        return null;
    }

    /**
     * Searches the database for all Patient entities and returns them.
     * @return List of Patient entities
     * @throws Exception
     */
    @Override
    public List<Patient> getAll() throws Exception {
        try {
            Firestore db = FirestoreClient.getFirestore();

            ApiFuture<QuerySnapshot> future = db.collection("patients").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            List<Patient> patientList = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) {
                patientList.add(document.toObject(Patient.class));
            }

            return patientList;
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
            Firestore db = FirestoreClient.getFirestore();

            // TODO: Checken hoe er hier met ID's om word gegaan, of die door firebase gegenerate word. Als we willen dat firebase het doet dan moet het met add() ipv set()
            ApiFuture<WriteResult> collectionsApiFuture = db.collection("patients").document().set(patient);

            // Comment naar Niels, nu returnen we het object dat je als parameter al meekrijgt en dat is beetje zinloos
            // in de voorbeeldvideo die ik had gekeken returnde hij de tijd van opslaan. ff kiezen/ bespreken wat we willen doen
            collectionsApiFuture.get().getUpdateTime().toString();
            return patient;
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
