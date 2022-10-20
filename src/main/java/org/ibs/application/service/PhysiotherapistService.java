package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.ibs.application.IPhysiotherapistService;
import org.ibs.application.dto.PlaceholderDTO;
import org.ibs.application.dto.physiotherapistdto.GetPhysiotherapist;
import org.ibs.application.dto.physiotherapistdto.SavePhysiotherapist;
import org.ibs.data.PersistPhysiotherapist;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PhysiotherapistService implements IPhysiotherapistService {


    private final Firestore db;

    public PhysiotherapistService() {
        db = FirestoreClient.getFirestore();
    }

    /**
     * Searches the database for a Physiotherapist with the given id and returns it if it exists.
     * @param id
     * @return Physiotherapist of given id
     * @throws Exception
     */
    @Override
    public GetPhysiotherapist getById(String id) throws Exception {
        try {
            DocumentReference documentReference = db.collection("physiotherapist").document(id);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                return document.toObject(GetPhysiotherapist.class);
            }

            else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception("Physiotherapist could not be found due to an error", e);
        }
    }

    /**
     * Searches the database for all Physiotherapist entities and returns them.
     * @return List of Physiotherapist entities
     * @throws Exception
     */
//    TODO: kan verwijderd worden
    @Override
    public List<GetPhysiotherapist> getAll() throws Exception {
        try {
            ApiFuture<QuerySnapshot> future = db.collection("physiotherapist").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            List<GetPhysiotherapist> physiotherapistsList = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) {
               physiotherapistsList.add(document.toObject(GetPhysiotherapist.class));
            }

            return physiotherapistsList;
        } catch (Exception e) {
            throw new Exception("Physiotherapists could not be found due to an error", e);
        }
    }

    /**
     * Saves and updates the given Physiotherapist entity in the database.
     * @param savePhysiotherapist
     * @return The saved Physiotherapist entity
     * @throws Exception
     */
    @Override
    public SavePhysiotherapist savePhysiotherapist(SavePhysiotherapist savePhysiotherapist) throws Exception {
        try {
            PersistPhysiotherapist persistPhysio = PersistPhysiotherapist.toPersistPhysio(savePhysiotherapist);

            ApiFuture<WriteResult> collectionsApiFuture = db.collection("physiotherapist").document(persistPhysio.getId()).set(persistPhysio);

            collectionsApiFuture.get().getUpdateTime().toString();

            return savePhysiotherapist;

        } catch (Exception e) {
            throw new Exception("Physiotherapist was not persisted due to an error", e);
        }
    }

    /**
     * Deletes the Physiotherapist entity with the given id.
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
