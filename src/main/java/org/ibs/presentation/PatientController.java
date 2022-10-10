package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IPatientService;
import org.ibs.application.dto.SavePatient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {
    private final IPatientService patientService;

//    @GetMapping("/{id}")
//    public PatientDTO getPatientById(@PathVariable String id) throws Exception {
//        return patientDTOMapper.toDTO(patientService.getById(id));
//    }
//
//    @GetMapping
//    public List<PatientDTO> getAllPatient() throws Exception {
//        return patientDTOMapper.toMultipleDTO(patientService.getAll());
//    }

    @PostMapping
    public SavePatient createPatient(@RequestBody SavePatient savePatient) throws Exception {
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
}
