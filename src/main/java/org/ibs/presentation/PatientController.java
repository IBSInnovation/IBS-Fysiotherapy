package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IPatientService;
import org.ibs.application.dto.measurementdto.AskMeasurement;
import org.ibs.application.dto.measurementdto.GetMeasurement;
import org.ibs.application.dto.measurementdto.SaveMeasurement;
import org.ibs.application.dto.patientdto.GetPatient;
import org.ibs.application.dto.patientdto.SavePatient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {
    private final IPatientService patientService;

    @GetMapping("/{id}")
    public GetPatient getPatientById(@PathVariable String id) throws Exception {
        return patientService.getById(id);
    }

    @GetMapping
    public List<GetPatient> getAllPatient() throws Exception {
        return patientService.getAll();
    }

    @PostMapping
    public SavePatient persistPatient(@RequestBody SavePatient savePatient) throws Exception {
        return patientService.savePatient(savePatient);
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

    @PostMapping("/measurement")
    public SaveMeasurement persistMeasurement(@RequestBody SaveMeasurement saveMeasurement) throws Exception {
        return patientService.saveMeasurement(saveMeasurement);
    }

    @DeleteMapping("/measurement")
    public boolean deleteMeasurement(@RequestBody SaveMeasurement saveMeasurement) throws Exception {
        return patientService.deleteMeasurement(saveMeasurement);
    }
}
