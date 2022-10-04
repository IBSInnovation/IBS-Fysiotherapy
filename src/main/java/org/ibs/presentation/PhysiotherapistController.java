package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IPhysiotherapistService;
import org.ibs.application.dto.PhysiotherapistDTO;
import org.ibs.application.dto.builder.PhysiotherapistDTOMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/physiotherapist")
@AllArgsConstructor
public class PhysiotherapistController {
    private final IPhysiotherapistService physiotherapistService;
    private final PhysiotherapistDTOMapper physiotherapistDTOMapper;

    @GetMapping("/{id}")
    public PhysiotherapistDTO getPatientById(@PathVariable long id) throws Exception {
        return physiotherapistDTOMapper.toDTO(physiotherapistService.getById(id));
    }

    @GetMapping
    public List<PhysiotherapistDTO> getAllPatient() throws Exception {
        return physiotherapistDTOMapper.toMultipleDTO(physiotherapistService.getAll());
    }

    @PostMapping
    public PhysiotherapistDTO createPatient(@RequestBody PhysiotherapistDTO physiotherapistDTO) throws Exception {
        return physiotherapistDTOMapper.toDTO(physiotherapistService.persistPhysiotherapist(physiotherapistDTOMapper.fromDTO(physiotherapistDTO)));
    }

    @PatchMapping
    public PhysiotherapistDTO updatePatient(@RequestBody PhysiotherapistDTO physiotherapistDTO) throws Exception {
        return physiotherapistDTOMapper.toDTO(physiotherapistService.persistPhysiotherapist(physiotherapistDTOMapper.fromDTO(physiotherapistDTO)));
    }

    @DeleteMapping("/{id}")
    public boolean deletePatient(@PathVariable long id) throws Exception {
        return physiotherapistService.deletePhysiotherapist(id);
    }
}
