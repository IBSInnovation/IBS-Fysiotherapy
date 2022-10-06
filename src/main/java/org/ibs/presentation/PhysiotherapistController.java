package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IPhysiotherapistService;
import org.ibs.application.dto.Physiotherapist.PhysiotherapistDTO;
import org.ibs.application.dto.builder.PhysiotherapistDTOMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/physiotherapist")
@AllArgsConstructor
public class PhysiotherapistController {
    private final IPhysiotherapistService physiotherapistService;
    private final PhysiotherapistDTOMapper physiotherapistDTOMapper;

//    TODO voeg meer error handling toe na de service laag
    @GetMapping("/{id}")
    public PhysiotherapistDTO getPhysiotherapistById(@PathVariable String id) throws Exception {
        return physiotherapistDTOMapper.toDTO(physiotherapistService.getById(id));
    }

    @GetMapping
    public List<PhysiotherapistDTO> getAllPatient() throws Exception {
        return physiotherapistDTOMapper.toMultipleDTO(physiotherapistService.getAll());
    }

    @PostMapping
    public PhysiotherapistDTO createPhysiotherapist(@RequestBody PhysiotherapistDTO physiotherapistDTO) throws Exception {
        return physiotherapistService.persistPhysiotherapist(physiotherapistDTO);
    }

    @PatchMapping
    public PhysiotherapistDTO updatePatient(@RequestBody PhysiotherapistDTO physiotherapistDTO) throws Exception {
        return physiotherapistService.persistPhysiotherapist(physiotherapistDTO);
    }

    @DeleteMapping("/{id}")
    public boolean deletePatient(@PathVariable String id) throws Exception {
        return physiotherapistService.deletePhysiotherapist(id);
    }
}
