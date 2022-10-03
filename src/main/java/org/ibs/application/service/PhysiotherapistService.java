package org.ibs.application.service;

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
    public Physiotherapist getById(long id) throws Exception {
        try {
            return physiotherapistRepository.findById(id).orElseThrow(Exception::new);
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
            return physiotherapistRepository.save(physiotherapist);
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
