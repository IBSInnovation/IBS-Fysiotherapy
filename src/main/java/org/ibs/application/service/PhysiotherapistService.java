package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.ibs.application.IPhysiotherapistService;
import org.ibs.application.dto.physiotherapistdto.GetPhysioPatient;
import org.ibs.application.dto.physiotherapistdto.GetPhysiotherapist;
import org.ibs.application.dto.physiotherapistdto.SavePhysioPatient;
import org.ibs.application.dto.physiotherapistdto.SavePhysiotherapist;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PhysiotherapistService implements IPhysiotherapistService {
    private final Firestore db;

    public PhysiotherapistService() {
        db = FirestoreClient.getFirestore();
    }

    /**
     * Searches the database for a Physiotherapist with the given id and returns it if it exists.
     *
     * @param id
     * @return Physiotherapist of given id
     * @throws Exception
     */
    @Override
    public GetPhysiotherapist getPhysioData(String id) throws Exception {
        try {
            System.out.println(id);
            DocumentReference documentReference = db.collection("physiotherapist").document(id);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                GetPhysiotherapist dto = document.toObject(GetPhysiotherapist.class);
                dto.id = id;
                return dto;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception("Physiotherapist could not be found due to an error", e);
        }
    }

    /**
     * Searches the database for a Physiotherapist with the given id and returns its patients if it exists.
     *
     * @param id
     * @return Physiotherapist's patients of given id
     * @throws Exception
     */
    @Override
    public List<GetPhysioPatient> getPhysioPatientData(String id) throws Exception {
        try {
            ApiFuture<QuerySnapshot> future = db.collection("physiotherapist").document(id).collection("patients").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            List<GetPhysioPatient> dtoList = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) {
                dtoList.add(document.toObject(GetPhysioPatient.class));
            }

            return dtoList;

        } catch (Exception e) {
            throw new Exception("Physiotherapists patients could not be found due to an error", e);
        }
    }

    /**
     * Saves and updates the given Physiotherapist entity in the database.
     *
     * @param savePhysiotherapist
     * @return The saved Physiotherapist entity
     * @throws Exception
     */
    @Override
    public GetPhysiotherapist savePhysiotherapist(SavePhysiotherapist savePhysiotherapist) throws Exception {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("email", savePhysiotherapist.email);
            data.put("name", savePhysiotherapist.name);
            ApiFuture<DocumentReference> addedDocRef = db.collection("physiotherapist").add(data);

            return new GetPhysiotherapist(
                    addedDocRef.get().getId(),
                    savePhysiotherapist.email,
                    savePhysiotherapist.name
            );
        } catch (Exception e) {
            throw new Exception("Physiotherapist was not persisted due to an error", e);
        }
    }

    /**
     * Saves the basic patient data in the physiotherapist object.
     *
     * @param savePhysioPatient
     * @return The saved patient data
     */
    @Override
    public SavePhysioPatient savePatientToPhysio(SavePhysioPatient savePhysioPatient) {
        Map<String, Object> data = new HashMap<>();
        data.put("email", savePhysioPatient.email);
        data.put("id", savePhysioPatient.patientId);
        data.put("name", savePhysioPatient.name);
        db.collection("physiotherapist")
                .document(savePhysioPatient.physioId)
                .collection("patients")
                .document(savePhysioPatient.patientId).set(data);
        return savePhysioPatient;
    }


    @Override
    public GetPhysiotherapist updatePhysiotherapist(GetPhysiotherapist getPhysiotherapist) {
        DocumentReference docRef = db.collection("physiotherapist").document(getPhysiotherapist.id);
        Map<String, Object> data = new HashMap<>();
        data.put("email", getPhysiotherapist.email);
        data.put("name", getPhysiotherapist.name);
        docRef.update(data);
        return getPhysiotherapist;
    }

    @Override
    public SavePhysioPatient updatePatientToPhysio(SavePhysioPatient savePhysioPatient) {
        DocumentReference docRef = db.collection("physiotherapist")
                .document(savePhysioPatient.physioId)
                .collection("patients").document(savePhysioPatient.patientId);
        Map<String, Object> data = new HashMap<>();
        data.put("email", savePhysioPatient.email);
        data.put("name", savePhysioPatient.name);
        docRef.update(data);
        return savePhysioPatient;
    }

    /**
     * Deletes the Physiotherapist entity with the given id.
     *
     * @param id
     * @return true if the operation succeeded
     * @throws Exception
     */
//    TODO: verwijder alle patienten van de physiotherapeut
    @Override
    public boolean deletePhysiotherapist(String id) throws Exception {
        try {
            ApiFuture<WriteResult> writeResult = db.collection("physiotherapist").document(id).delete();
            writeResult.get().getUpdateTime().toString();
            return true;
        } catch (Exception e) {
            throw new Exception("Physiotherapist could not be deleted due to an error", e);
        }
    }
}
