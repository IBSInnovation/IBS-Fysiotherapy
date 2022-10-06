package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IPatientService;
import org.ibs.application.dto.Patient.PatientDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public PatientDTO createPatient(@RequestBody PatientDTO patientDTO) throws Exception {
        return patientService.savePatient(patientDTO);
    }

    @PatchMapping
    public PatientDTO updatePatient(@RequestBody PatientDTO patientDTO) throws Exception {
        return patientService.savePatient(patientDTO);
    }

    @DeleteMapping("/{id}")
    public boolean deletePatient(@PathVariable String id) throws Exception {
        return patientService.deletePatient(id);
    }
}
