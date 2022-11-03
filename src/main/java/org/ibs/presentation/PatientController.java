package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IJoinService;
import org.ibs.application.IPatientService;
import org.ibs.application.dto.PlaceholderDTO;
import org.ibs.application.dto.patientdto.GetPatient;
import org.ibs.application.dto.patientdto.SavePatient;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://127.0.0.1:5500/")
@RestController
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {
    private final IPatientService patientService;
    private final IJoinService joinService;

    @GetMapping("/{id}")
    public GetPatient getPatientById(@PathVariable String id) throws Exception {
        return patientService.getPatientData(id);
    }

    @PostMapping
    public GetPatient savePatient(@RequestBody SavePatient savePatient) throws Exception {
        return patientService.savePatient(savePatient);
    }

    @PatchMapping
    public PlaceholderDTO updatePatient(@RequestBody GetPatient getPatient) {
        return joinService.updatePatient(getPatient);
    }

    @DeleteMapping("/{id}")
    public boolean deletePatient(@PathVariable String id) {
        return joinService.deletePatientAndSubcollections(id);
    }
}
