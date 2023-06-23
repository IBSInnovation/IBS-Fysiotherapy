package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.dto.measurementdto.AskMeasurement;
import org.ibs.application.dto.measurementdto.DeleteMeasurement;
import org.ibs.application.dto.measurementdto.GetMeasurement;
import org.ibs.application.dto.measurementdto.SaveMeasurement;
import org.ibs.application.dto.patientdto.GetPatient;
import org.ibs.application.dto.patientdto.SavePatient;
import org.ibs.application.service.PatientService;
import org.ibs.domain.Measurement;
import org.ibs.domain.Patient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping("/{id}")
    public GetPatient getPatientById(@PathVariable String id) throws Exception {
        return patientService.getById(id);
    }

    @GetMapping
    public List<GetPatient> getAllPatient() throws Exception {
        return patientService.getAll();
    }

    @PostMapping
    public SavePatient savePatient(@RequestBody SavePatient savePatient) throws Exception {
        return patientService.savePatient(savePatient);
    }

    @PatchMapping("/{id}")
    public Patient updatePatient(@PathVariable String id, @RequestBody SavePatient savePatient) throws Exception {
        return patientService.updatePatient(id, savePatient);
    }

    @DeleteMapping("/{id}")
    public boolean deletePatient(@PathVariable String id) throws Exception {
        return patientService.deletePatient(id);
    }

//    TODO: misschien patientid in pathvariable?
    @GetMapping("/measurement")
    public GetMeasurement getMeasurementsOfExercise(@RequestBody AskMeasurement askMeasurement) throws Exception {
        return patientService.getAllMeasurements(askMeasurement);
    }

    @PostMapping("/measurement/{patientId}")
    public List<Measurement> saveMeasurement(@PathVariable String patientId, @RequestBody SaveMeasurement measurement) throws Exception {
        return patientService.saveMeasurement(patientId, measurement);
    }


    @DeleteMapping("/measurement/{id}")
    public boolean deleteMeasurement(@PathVariable Long id, @RequestBody DeleteMeasurement measurement) throws Exception {
        return patientService.deleteMeasurement(id, measurement);
    }
}
