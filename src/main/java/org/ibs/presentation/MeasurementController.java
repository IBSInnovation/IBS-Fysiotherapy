package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IMeasurementService;
import org.ibs.application.dto.MeasurementDTO;
import org.ibs.application.dto.mapper.MeasurementDTOMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurement")
@AllArgsConstructor
public class MeasurementController {
    private final IMeasurementService measurementService;
    private final MeasurementDTOMapper measurementDTOMapper;

    @GetMapping("/{id}")
    public MeasurementDTO getMeasurementById(@PathVariable long id) throws Exception {
        return measurementDTOMapper.toDTO(measurementService.getById(id));
    }

    @GetMapping
    public List<MeasurementDTO> getAllMeasurements() throws Exception {
        return measurementDTOMapper.toMultipleDTO(measurementService.getAll());
    }

    @PostMapping
    public MeasurementDTO createMeasurement(@RequestBody MeasurementDTO measurementDTO) throws Exception {
        return measurementDTOMapper.toDTO(measurementService.saveMeasurement(measurementDTOMapper.fromDTO(measurementDTO)));
    }

    @PatchMapping
    public MeasurementDTO updateMeasurement(@RequestBody MeasurementDTO measurementDTO) throws Exception {
        return measurementDTOMapper.toDTO(measurementService.saveMeasurement(measurementDTOMapper.fromDTO(measurementDTO)));
    }

    @DeleteMapping("/{id}")
    public boolean deleteMeasurement(@PathVariable long id) throws Exception {
        return measurementService.deleteMeasurement(id);
    }
}
