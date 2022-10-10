package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.ibs.application.IMeasurementService;
import org.ibs.application.dto.SaveMeasurement;
import org.ibs.data.PersistMeasurement;
import org.ibs.domain.Measurement;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MeasurementService implements IMeasurementService {
    private final Firestore db;

    public MeasurementService() {
        this.db = FirestoreClient.getFirestore();
    }

    /**
     * Searches the database for a Measurement entity with the given id and returns it if it exists.
     * @param id
     * @return Measurement of given id
     * @throws Exception
     */
    @Override
    public Measurement getById(String id) throws Exception {
        try {
            DocumentReference documentReference = db.collection("measurement").document(id);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                return document.toObject(Measurement.class);
            }
//            TODO add costum errors
            else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception("Measurement could not be found due to an error", e);
        }
    }

    /**
     * Searches the database for all Measurement entities and returns them.
     * @return List of Measurement entities
     * @throws Exception
     */
    @Override
    public List<Measurement> getAll() throws Exception {
        try {
            ApiFuture<QuerySnapshot> future = db.collection("patients").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            List<Measurement> measurementList = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) {
                measurementList.add(document.toObject(Measurement.class));
            }

            return measurementList;
        } catch (Exception e) {
            throw new Exception("Measurements could not be found due to an error", e);
        }
    }

    /**
     * Saves and updates the given Measurement entity in the database.
     * @param saveMeasurement
     * @return THe saves Measurement entity
     * @throws Exception
     */
    @Override
    public SaveMeasurement saveMeasurement(SaveMeasurement saveMeasurement) throws Exception {
        try {
            PersistMeasurement measurement = PersistMeasurement.toPersistMeasurement(saveMeasurement);

            ApiFuture<WriteResult> collectionsApiFuture = db.collection("measurement").document(measurement.getId()).set(measurement);

            // TODO: log dit
            collectionsApiFuture.get().getUpdateTime().toString();

            return saveMeasurement;
        } catch (Exception e) {
            throw new Exception("Measurement was not persisted due to an error", e);
        }
    }

    /**
     * Deletes the Measurement entity with the given id.
     * @param id
     * @return true if the operation succeeded
     * @throws Exception
     */
    @Override
    public boolean deleteMeasurement(String id) throws Exception {
        try {
            ApiFuture<WriteResult> writeResult = db.collection("measurement").document(id).delete();
            // TODO: log dit
            writeResult.get().getUpdateTime().toString();
            return true;
        } catch (Exception e) {
            throw new Exception("Measurement could not be deleted due to an error", e);
        }
    }
}
