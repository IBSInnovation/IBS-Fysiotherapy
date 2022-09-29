package org.ibs.application.service;

import lombok.AllArgsConstructor;
import org.ibs.application.IMeasurementService;
import org.ibs.application.dto.MeasurementDTO;
import org.ibs.domain.Measurement;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class MeasurementService implements IMeasurementService {
    @Override
    public Measurement getById(long id) {
        return null;
    }

    @Override
    public List<Measurement> getAll() {
        return null;
    }

    @Override
    public Measurement persistMeasurement(MeasurementDTO measurementDTO) {
        return null;
    }

    @Override
    public boolean deleteMeasurement(long id) {
        return false;
    }
}
