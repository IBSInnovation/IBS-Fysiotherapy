package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.ibs.application.IPatientService;
import org.ibs.application.dto.patientdto.GetPatient;
import org.ibs.application.dto.patientdto.SavePatient;
import org.ibs.data.PersistPatient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PatientService implements IPatientService {
//test
    private final Firestore db;

    public PatientService() {
        db = FirestoreClient.getFirestore();
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
            DocumentReference documentReference = db.collection("patient").document(id);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                return document.toObject(GetPatient.class);
            }
            else {
                throw new Exception();
            }
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

//    TODO: kan verwijderd worden
    @Override
    public List<GetPatient> getAll() throws Exception {
        try {
            ApiFuture<QuerySnapshot> future = db.collection("patient").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            List<GetPatient> patientList = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) {
                patientList.add(document.toObject(GetPatient.class));
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
            PersistPatient patient = PersistPatient.toPersistPatient(savePatient);

            ApiFuture<DocumentReference> addedDocRef = db.collection("patient").add(patient);

            return savePatient;
        } catch (Exception e) {
            throw new Exception("Patient was not persisted due to an error", e);
        }
    }

    /**
     * Deletes the Patient entity with the given id.
     *
     * @param patientId physioId
     * @return true if the operation succeeded
     * @throws Exception
     */
    @Override
    public boolean deletePatient(String patientId, String physioId) throws Exception {
        try {
            ApiFuture<WriteResult> writeResult = db.collection("patient").document(patientId).delete();

            ApiFuture<WriteResult> writeResult2 = db
                    .collection("physiotherapist")
                    .document(physioId)
                    .collection("patients")
                    .document(patientId)
                    .delete();

//            writeResult.get().getUpdateTime().toString();
            return true;
        } catch (Exception e) {
            throw new Exception("Patient could not be deleted due to an error", e);
        }
    }

//    TODO: eigen service voor measurements

}
