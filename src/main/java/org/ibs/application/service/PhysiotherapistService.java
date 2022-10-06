package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.AllArgsConstructor;
import org.ibs.application.IPhysiotherapistService;
import org.ibs.domain.Physiotherapist;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PhysiotherapistService implements IPhysiotherapistService {

    private Firestore db;

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
    public Physiotherapist getById(String id) throws Exception {
        try {
            DocumentReference documentReference = db.collection("fysio").document(id);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            Physiotherapist physiotherapist;
            if (document.exists()) {
                physiotherapist = document.toObject(Physiotherapist.class);
                return physiotherapist;
            }

//            TODO add costum errors
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
    @Override
    public List<Physiotherapist> getAll() throws Exception {
        try {
            ApiFuture<QuerySnapshot> future = db.collection("fysio").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            List<Physiotherapist> physiotherapistsList = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) {
               physiotherapistsList.add(document.toObject(Physiotherapist.class));
            }

            return physiotherapistsList;
        } catch (Exception e) {
            throw new Exception("Physiotherapists could not be found due to an error", e);
        }
//        catch (NullPointerException e) {
//            throw new Exception("eigen message", e);
//        }
    }

    /**
     * Saves and updates the given Physiotherapist entity in the database.
     * @param physiotherapist
     * @return The saved Physiotherapist entity
     * @throws Exception
     */
    @Override
    public Physiotherapist persistPhysiotherapist(Physiotherapist physiotherapist) throws Exception {
        try {
            // TODO: Checken hoe er hier met ID's om word gegaan, of die door firebase gegenerate word. Als we willen dat firebase het doet dan moet het met add() ipv set()
            ApiFuture<WriteResult> collectionsApiFuture = db.collection("fysio").document().set(physiotherapist);

            // TODO: log dit
            collectionsApiFuture.get().getUpdateTime().toString();
            return physiotherapist;

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
    @Override
    public boolean deletePhysiotherapist(String id) throws Exception {
        try {
            ApiFuture<WriteResult> writeResult = db.collection("fysio").document(id).delete();

            // TODO: log dit
            writeResult.get().getUpdateTime().toString();
            return true;
        } catch (Exception e) {
            throw new Exception("Physiotherapist could not be deleted due to an error", e);
        }
    }
}
