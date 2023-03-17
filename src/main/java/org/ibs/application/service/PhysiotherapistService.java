package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.ibs.application.IPhysiotherapistService;
import org.ibs.application.dto.physiotherapistdto.GetPhysiotherapist;
import org.ibs.application.dto.physiotherapistdto.SavePhysiotherapist;
import org.ibs.data.PersistPhysiotherapist;
import org.ibs.data.PhysiotherapistRepository;
import org.ibs.domain.Physiotherapist;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PhysiotherapistService implements IPhysiotherapistService {

//    TODO: getter dtos fixen

    private PhysiotherapistRepository physiotherapistRepository;

    public PhysiotherapistService(PhysiotherapistRepository physiotherapistRepository) {
        this.physiotherapistRepository = physiotherapistRepository;
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
            Physiotherapist physiotherapist = physiotherapistRepository.getById(Long.parseLong(id));
            return new GetPhysiotherapist(physiotherapist.getEmail());
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
    public List<GetPhysiotherapist> getAll() throws Exception {
        try {
            List<Physiotherapist> physiotherapists = physiotherapistRepository.findAll();
            List<GetPhysiotherapist> physiotherapistsList = new ArrayList<>();
            for( Physiotherapist physiotherapist : physiotherapists) {
               physiotherapistsList.add(new GetPhysiotherapist(physiotherapist.getEmail()));
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
            Physiotherapist physiotherapist = new Physiotherapist(savePhysiotherapist.email);
            physiotherapistRepository.save(physiotherapist);
            return new SavePhysiotherapist();
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
            Physiotherapist physiotherapist = physiotherapistRepository.getById(Long.parseLong(id));
            physiotherapistRepository.delete(physiotherapist);
            return true;
        } catch (Exception e) {
            throw new Exception("Physiotherapist could not be deleted due to an error", e);
        }
    }
}
