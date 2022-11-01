package org.ibs.application.service;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.ibs.application.IMeasurementService;
import org.ibs.application.dto.measurementdto.GetMeasurement;
import org.ibs.application.dto.measurementdto.SaveMeasurement;
import org.ibs.application.dto.patientdto.SaveMeasurementPatient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class MeasurementService implements IMeasurementService {
    private final Firestore db;
    
    public MeasurementService() {
        db = FirestoreClient.getFirestore();
    }

    @Override
    public GetMeasurement getMeasurementData(String id) throws Exception {
        try {
            DocumentReference documentReference = db.collection("measurements").document(id);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                GetMeasurement dto = document.toObject(GetMeasurement.class);
                dto.id = id;
                return dto;
            } else {
                throw new Exception();
            }
        }catch (Exception e) {
            throw new Exception("Measurement could not be found due to an error", e);
        }
    }

    @Override
    public GetMeasurement saveMeasurement(SaveMeasurement saveMeasurement) throws Exception {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("data", saveMeasurement.data);
            data.put("dateOfMeasurement", saveMeasurement.dateOfMeasurement);
            data.put("exercise", saveMeasurement.exerciseId);

            ApiFuture<DocumentReference> addedDocRef = db.collection("measurements").add(data);

            return new GetMeasurement(
                    addedDocRef.get().getId(),
                    saveMeasurement.dateOfMeasurement,
                    saveMeasurement.data,
                    saveMeasurement.exerciseId);
        } catch (Exception e) {
            throw new Exception("Measurement was not persisted due to an error", e);
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

    @Override
    public GetMeasurement updateMeasurement(GetMeasurement getMeasurement) throws Exception {
        try {
            DocumentReference docRef = db.collection("measurements").document(getMeasurement.id);
            Map<String, Object> data = new HashMap<>();
            data.put("data", getMeasurement.data);
            data.put("dateOfMeasurement", getMeasurement.dateOfMeasurement);
            data.put("exercise", getMeasurement.exercise);
            docRef.update(data);
            return getMeasurement;
        } catch (Exception e) {
            throw new Exception("Could not update measurement due to an error", e);
        }
    }

    /**
     * Deletes the Measurement entity with the given id.
     *
     * @param id
     * @return true if the operation succeeded
     * @throws Exception
     */
    @Override
    public boolean deleteMeasurement(String id) throws Exception {
        try {
            db.collection("measurements").document(id).delete();
            return true;
        } catch (Exception e) {
            throw new Exception("Measurement could not be deleted due to an error", e);
        }
    }

    @Override
    public boolean removeMeasurementFromPatient(String measurementId, String patientId) throws Exception {
        try {
            db.collection("patient")
                    .document(patientId)
                    .collection("measurements")
                    .document(measurementId)
                    .delete();
            return true;
        } catch (Exception e) {
            throw new Exception("Measurement could not be removed from patient due to an error", e);
        }
    }
}
