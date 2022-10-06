package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IPatientService;
import org.ibs.application.dto.PatientDTO;
import org.ibs.application.dto.builder.PatientDTOMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {
    private final IPatientService patientService;
    private final PatientDTOMapper patientDTOMapper;

    @GetMapping("/{id}")
    public PatientDTO getPatientById(@PathVariable String id) throws Exception {
        return patientDTOMapper.toDTO(patientService.getById(id));
    }

    @GetMapping
    public List<PatientDTO> getAllPatient() throws Exception {
        return patientDTOMapper.toMultipleDTO(patientService.getAll());
    }

    @PostMapping
    public PatientDTO createPatient(@RequestBody PatientDTO patientDTO) throws Exception {
        return patientDTOMapper.toDTO(patientService.persistPatient(patientDTOMapper.fromDTO(patientDTO)));
    }

    @PatchMapping
    public PatientDTO updatePatient(@RequestBody PatientDTO patientDTO) throws Exception {
        return patientDTOMapper.toDTO(patientService.persistPatient(patientDTOMapper.fromDTO(patientDTO)));
    }

    @DeleteMapping("/{id}")
    public boolean deletePatient(@PathVariable String id) throws Exception {
        return patientService.deletePatient(id);
    }
}
