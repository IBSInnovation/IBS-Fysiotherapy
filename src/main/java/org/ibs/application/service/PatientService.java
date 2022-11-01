package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.ibs.application.IPatientService;
import org.ibs.application.dto.patientdto.GetPatient;
import org.ibs.application.dto.patientdto.GetPatientMeasurementData;
import org.ibs.application.dto.patientdto.SaveMeasurementPatient;
import org.ibs.application.dto.patientdto.SavePatient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PatientService implements IPatientService {
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
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception("Patient could not be found due to an error", e);
        }
    }

    @Override
    public List<GetPatientMeasurementData> getPatientMeasurementData(String id) throws Exception {
        try {
            ApiFuture<QuerySnapshot> future = db.collection("patient").document(id).collection("measurements").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            List<GetPatientMeasurementData> dataList = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) {
                dataList.add(document.toObject(GetPatientMeasurementData.class));
            }
            return dataList;
        }catch (Exception e) {
            throw new Exception("Patient was not persisted due to an error", e);
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
    public GetPatient savePatient(SavePatient savePatient) throws Exception {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("dateOfBirth", savePatient.dateOfBirth);
            data.put("email", savePatient.email);
            data.put("name", savePatient.name);
            data.put("surName", savePatient.surName);
            data.put("weight", savePatient.weight);
            ApiFuture<DocumentReference> addedDocRef = db.collection("patient").add(data);

            return new GetPatient(
                    addedDocRef.get().getId(),
                    savePatient.name,
                    savePatient.surName,
                    savePatient.weight,
                    savePatient.dateOfBirth,
                    savePatient.email
            );
        } catch (Exception e) {
            throw new Exception("Patient was not persisted due to an error", e);
        }
    }

    @Override
    public SaveMeasurementPatient saveMeasurementToPatient(SaveMeasurementPatient saveMeasurementPatient) throws Exception {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("exercise", saveMeasurementPatient.exerciseId);
            data.put("measurement", saveMeasurementPatient.measurementId);

            db.collection("patient")
                    .document(saveMeasurementPatient.patientId)
                    .collection("measurements")
                    .document(saveMeasurementPatient.measurementId).set(data);

            return saveMeasurementPatient;
        } catch (Exception e) {
            throw new Exception("Patient was not persisted due to an error", e);
        }
    }


    /**
     * @param getPatient
     * @return
     * @throws Exception
     */
    @Override
    public GetPatient updatePatient(GetPatient getPatient) throws Exception {
        try {
            DocumentReference docRef = db.collection("patient").document(getPatient.id);
            Map<String, Object> data = new HashMap<>();
            data.put("dateofBirth", getPatient.dateOfBirth);
            data.put("email", getPatient.email);
            data.put("name", getPatient.name);
            data.put("surname", getPatient.surName);
            data.put("weight", getPatient.weight);

            docRef.update(data);
            return getPatient;
        } catch (Exception e) {
            throw new Exception("Could not update Patient due to an error", e);
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
    public boolean deletePatient(String patientId) throws Exception {
        try {
            db.collection("patient").document(patientId).delete();

            return true;
        } catch (Exception e) {
            throw new Exception("Patient could not be deleted due to an error", e);
        }
    }

    @Override
    public boolean removePatientFromPhysio(String patientId, String physioId) throws Exception {
        try {
            db.collection("physiotherapist")
                    .document(physioId)
                    .collection("patients")
                    .document(patientId)
                    .delete();

            return true;
        } catch (Exception e) {
            throw new Exception("Patient could not be deleted due to an error", e);
        }
    }
}
