package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IJoinService;
import org.ibs.application.dto.joindto.ExerciseAndMeasurementData;
import org.ibs.application.dto.joindto.HomePageData;
import org.ibs.application.dto.joindto.PatientPageData;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://127.0.0.1:5500/")
@RestController
@RequestMapping("/load")
@AllArgsConstructor
public class PageLoaderController {
    private final IJoinService joinService;

    @GetMapping("/home/{physioId}")
    public HomePageData getDataHomePage(@PathVariable String physioId) throws Exception {
        return joinService.getDataForHomePage(physioId);
    }

    @GetMapping("/patient/{patientId}")
    public PatientPageData getDataPatientPage(@PathVariable String patientId) throws Exception {
        return joinService.getDataForPatientPage(patientId);
    }

    @GetMapping("/measurement/{measurementId}")
    public ExerciseAndMeasurementData getMeasurementData(@PathVariable String measurementId) throws Exception {
        return joinService.getDataForMeasurement(measurementId);
    }
}
