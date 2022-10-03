package org.ibs.application.service;

import lombok.AllArgsConstructor;
import org.ibs.application.IMeasurementService;
import org.ibs.data.MeasurementRepository;
import org.ibs.domain.Measurement;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class MeasurementService implements IMeasurementService {
    private final MeasurementRepository measurementRepository;

    /**
     * Searches the database for a Measurement entity with the given id and returns it if it exists.
     * @param id
     * @return Measurement of given id
     * @throws Exception
     */
    @Override
    public Measurement getById(long id) throws Exception {
        try {
            return measurementRepository.findById(id).orElseThrow(Exception::new);
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
            return measurementRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Measurements could not be found due to an error", e);
        }
    }

    /**
     * Saves and updates the given Measurement entity in the database.
     * @param measurement
     * @return THe saves Measurement entity
     * @throws Exception
     */
    @Override
    public Measurement persistMeasurement(Measurement measurement) throws Exception {
        try {
            return measurementRepository.save(measurement);
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
    public boolean deleteMeasurement(long id) throws Exception {
        try {
            measurementRepository.delete(measurementRepository.findById(id).orElseThrow(Exception::new));
            return true;
        } catch (Exception e) {
            throw new Exception("Measurement could not be deleted due to an error", e);
        }
    }
}
