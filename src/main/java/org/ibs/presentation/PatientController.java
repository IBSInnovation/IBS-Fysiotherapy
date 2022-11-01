package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IPatientService;
import org.ibs.application.dto.patientdto.GetPatient;
import org.ibs.application.dto.patientdto.SavePatient;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://127.0.0.1:5500/")
@RestController
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {
    private final IPatientService patientService;

    @GetMapping("/{id}")
    public GetPatient getPatientById(@PathVariable String id) throws Exception {
        return patientService.getPatientData(id);
    }

    @PostMapping
    public SavePatient savePatient(@RequestBody SavePatient savePatient) throws Exception {
        return patientService.savePatient(savePatient);
    }

    @PatchMapping
    public SavePatient updatePatient(@RequestBody SavePatient savePatient) throws Exception {
        return patientService.savePatient(savePatient);
    }

    @DeleteMapping("/{id}")
    public boolean deletePatient(@PathVariable String id) throws Exception {
        return patientService.deletePatient(id);
    }

////    TODO: eigen controller voor emasurements
//    @GetMapping("/measurement")
//    public GetMeasurement getMeasurementsOfExercise(@RequestBody AskMeasurement askMeasurement) throws Exception {
//        return patientService.getAllMeasurements(askMeasurement);
//    }
//
//    @PostMapping("/measurement")
//    public SaveMeasurement saveMeasurement(@RequestBody SaveMeasurement saveMeasurement) throws Exception {
//        return patientService.saveMeasurement(saveMeasurement);
//    }
//
//    @PatchMapping("/measurement")
//    public SaveMeasurement updateMeasurement(@RequestBody SaveMeasurement saveMeasurement) throws Exception {
//        return patientService.saveMeasurement(saveMeasurement);
//    }
//
//    @DeleteMapping("/measurement")
//    public boolean deleteMeasurement(@RequestBody SaveMeasurement saveMeasurement) throws Exception {
//        return patientService.deleteMeasurement(saveMeasurement);
//    }
}
