package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.ibs.application.IPatientService;
import org.ibs.application.dto.patientdto.GetPatient;
import org.ibs.application.dto.patientdto.GetPatientMeasurementData;
import org.ibs.application.dto.patientdto.SavePatient;
import org.ibs.data.PersistPatient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public GetPatient getPatientData(String id) throws Exception {
        try {
            DocumentReference documentReference = db.collection("patient").document(id);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                GetPatient dto = document.toObject(GetPatient.class);
                dto.id = id;
                return dto;
            }
            else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception("Patient could not be found due to an error", e);
        }
    }

    public List<GetPatientMeasurementData> getPatientMeasurementData(String id) {
//        haal patient/id/measurements op en return de lijst die je krijgt
        return null;
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
//            kijk naar hoe savePhysio eruit ziet
            PersistPatient patient = PersistPatient.toPersistPatient(savePatient);

            ApiFuture<DocumentReference> addedDocRef = db.collection("patient").add(patient);

            return savePatient;
        } catch (Exception e) {
            throw new Exception("Patient was not persisted due to an error", e);
        }
    }
    

    /**
     *
     * @param getPatient
     * @return
     * @throws Exception
     */
    @Override
    public GetPatient updatePatient(GetPatient getPatient) throws Exception {
        DocumentReference docRef = db.collection("patient").document(getPatient.id);
        Map<String, Object> data = new HashMap<>();
        data.put("dateofBirth", getPatient.dateOfBirth);
        data.put("email", getPatient.email);
        data.put("name", getPatient.name);
        data.put("surname", getPatient.surName);
        data.put("weight", getPatient.weight);

        docRef.update(data);
        return getPatient;
    }

//    update measurement in patient (kijk naar updatePatientToPhysio voor idee

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
}
