package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IMeasurementService;
import org.ibs.application.dto.measurementdto.GetMeasurement;
import org.ibs.application.dto.measurementdto.SaveMeasurement;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://127.0.0.1:5500/")
@RestController
@RequestMapping("/measurement")
@AllArgsConstructor
public class MeasurementController {
    private final IMeasurementService measurementService;

    @GetMapping("/{id}")
    public GetMeasurement getMeasurementById(@PathVariable String id) throws Exception {
        return measurementService.getMeasurementData(id);
    }

    @PostMapping("/measurement")
    public GetMeasurement saveMeasurement(@RequestBody SaveMeasurement saveMeasurement) throws Exception {
        return measurementService.saveMeasurement(saveMeasurement);
    }

    @PatchMapping("/measurement")
    public GetMeasurement updateMeasurement(@RequestBody GetMeasurement getMeasurement) throws Exception {
        return measurementService.updateMeasurement(getMeasurement);
    }

    @DeleteMapping("/{id}")
    public boolean deleteMeasurement(@PathVariable String id) throws Exception {
        return measurementService.deleteMeasurement(id);
    }
}
