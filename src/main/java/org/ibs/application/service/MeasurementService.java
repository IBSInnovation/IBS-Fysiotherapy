package org.ibs.application.service;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.ibs.application.IMeasurementService;
import org.ibs.application.dto.measurementdto.AskMeasurement;
import org.ibs.application.dto.measurementdto.GetMeasurement;
import org.ibs.application.dto.measurementdto.SaveMeasurement;
import org.ibs.data.PersistMeasurement;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MeasurementService implements IMeasurementService {

    private final Firestore db;

    public MeasurementService() {
        db = FirestoreClient.getFirestore();
    }

    @Override
    public SaveMeasurement saveMeasurement(SaveMeasurement saveMeasurement) throws Exception {
        try {
            PersistMeasurement measurement = PersistMeasurement.toPersistMeasurement(saveMeasurement);

            DocumentReference measurementRef = db
                    .collection("patient").document(saveMeasurement.patientId)
                    .collection("category").document("doc")
                    .collection(saveMeasurement.categoryId).document(saveMeasurement.exerciseId);


            ApiFuture<WriteResult> collectionsApiFuture = measurementRef.update("measurements", FieldValue.arrayUnion(measurement));

            collectionsApiFuture.get().getUpdateTime().toString();

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
            PersistMeasurement measurement = PersistMeasurement.toPersistMeasurement(saveMeasurement);

            DocumentReference measurementRef = db
                    .collection("patient").document(saveMeasurement.patientId)
                    .collection("category").document("doc")
                    .collection(saveMeasurement.categoryId).document(saveMeasurement.exerciseId);


            ApiFuture<WriteResult> collectionsApiFuture = measurementRef.update("measurements", FieldValue.arrayRemove(measurement));
            collectionsApiFuture.get().getUpdateTime().toString();
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
        try {

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
        }
    }


}
