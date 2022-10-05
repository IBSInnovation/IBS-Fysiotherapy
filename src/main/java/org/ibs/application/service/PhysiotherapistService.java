package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import lombok.AllArgsConstructor;
import org.ibs.application.IPhysiotherapistService;
import org.ibs.data.PhysiotherapistRepository;
import org.ibs.domain.Physiotherapist;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PhysiotherapistService implements IPhysiotherapistService {

    private final PhysiotherapistRepository physiotherapistRepository;

    /**
     * Searches the database for a Physiotherapist with the given id and returns it if it exists.
     * @param id
     * @return Physiotherapist of given id
     * @throws Exception
     */
    @Override
    public Physiotherapist getById(String id) throws Exception {
        try {
            Firestore db = FirestoreClient.getFirestore();
            DocumentReference documentReference = db.collection("fysio").document(id);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot document = future.get();

            Physiotherapist physiotherapist;
            if (document.exists()) {
                physiotherapist = document.toObject(Physiotherapist.class);
                return physiotherapist;
            }
        } catch (Exception e) {
            throw new Exception("Physiotherapist could not be found due to an error", e);
        }
        return null;
    }

    /**
     * Searches the database for all Physiotherapist entities and returns them.
     * @return List of Physiotherapist entities
     * @throws Exception
     */
    @Override
    public List<Physiotherapist> getAll() throws Exception {
        try {
            return physiotherapistRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Physiotherapists could not be found due to an error", e);
        }
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
            Firestore db = FirestoreClient.getFirestore();

            // TODO: Checken hoe er hier met ID's om word gegaan, of die door firebase gegenerate word
            ApiFuture<WriteResult> collectionsApiFuture = db.collection("fysio").document().set(physiotherapist);

            // Comment naar Niels, nu returnen we het object dat je als parameter al meekrijgt en dat is beetje zinloos
            // in de voorbeeldvideo die ik had gekeken returnde hij de tijd van opslaan. ff kiezen/ bespreken wat we willen doen
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
    public boolean deletePhysiotherapist(long id) throws Exception {
        try {
            physiotherapistRepository.delete(physiotherapistRepository.findById(id).orElseThrow(Exception::new));
            return true;
        } catch (Exception e) {
            throw new Exception("Physiotherapist could not be deleted due to an error", e);
        }
    }
}
