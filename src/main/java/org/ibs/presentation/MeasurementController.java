package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IMeasurementService;
import org.ibs.application.dto.measurementdto.SaveMeasurement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/measurement")
@AllArgsConstructor
public class MeasurementController {
    private final IMeasurementService measurementService;

//    @GetMapping("/{id}")
//    public SaveMeasurement getMeasurementById(@PathVariable long id) throws Exception {
//        return measurementService.getById(id);
//    }
//
//    @GetMapping
//    public List<SaveMeasurement> getAllMeasurements() throws Exception {
//        return measurementService.getAll();
//    }

    @PostMapping
    public SaveMeasurement saveMeasurement(@RequestBody SaveMeasurement saveMeasurement) throws Exception {
        return measurementService.saveMeasurement(saveMeasurement);
    }

    @PatchMapping
    public SaveMeasurement updateMeasurement(@RequestBody SaveMeasurement saveMeasurement) throws Exception {
        return measurementService.saveMeasurement(saveMeasurement);
    }

    @DeleteMapping("/{id}")
    public boolean deleteMeasurement(@PathVariable String id) throws Exception {
        return measurementService.deleteMeasurement(id);
    }
}
