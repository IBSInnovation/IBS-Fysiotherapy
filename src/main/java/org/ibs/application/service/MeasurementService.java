package org.ibs.application.service;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.ibs.application.IMeasurementService;
import org.ibs.application.dto.measurementdto.AskMeasurement;
import org.ibs.application.dto.measurementdto.GetMeasurement;
import org.ibs.application.dto.measurementdto.SaveMeasurement;
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

    /**
     * Deletes the Measurement entity with the given id.
     * @param askMeasurement
     * @return true if the operation succeeded
     * @throws Exception
     */
    @Override
    public boolean deleteMeasurement(AskMeasurement askMeasurement) throws Exception {
        try {
            ApiFuture<WriteResult> writeResult = db
                    .collection("measurements")
                    .document(askMeasurement.id)
                    .delete();

            ApiFuture<WriteResult> writeResult2 = db
                    .collection("patient")
                    .document(askMeasurement.patientId)
                    .collection("measurements")
                    .document(askMeasurement.id)
                    .delete();

            return true;
        } catch (Exception e) {
            throw new Exception("Measurement could not be deleted due to an error", e);
        }
    }
}
