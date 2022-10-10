package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IPhysiotherapistService;
import org.ibs.application.dto.SavePhysiotherapist;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/physiotherapist")
@AllArgsConstructor
public class PhysiotherapistController {
    private final IPhysiotherapistService physiotherapistService;
//    private final PhysiotherapistDTOMapper physiotherapistDTOMapper;

//    TODO voeg meer error handling toe na de service laag
//    @GetMapping("/{id}")
//    public PhysiotherapistDTO getPhysiotherapistById(@PathVariable String id) throws Exception {
//        return physiotherapistDTOMapper.toDTO(physiotherapistService.getById(id));
//    }
//
//    @GetMapping
//    public List<PhysiotherapistDTO> getAllPatient() throws Exception {
//        return physiotherapistDTOMapper.toMultipleDTO(physiotherapistService.getAll());
//    }

    @PostMapping
    public SavePhysiotherapist createPhysiotherapist(@RequestBody SavePhysiotherapist savePhysiotherapist) throws Exception {
        return physiotherapistService.savePhysiotherapist(savePhysiotherapist);
    }

    @PatchMapping
    public SavePhysiotherapist updatePatient(@RequestBody SavePhysiotherapist savePhysiotherapist) throws Exception {
        return physiotherapistService.savePhysiotherapist(savePhysiotherapist);
    }

    @DeleteMapping("/{id}")
    public boolean deletePatient(@PathVariable String id) throws Exception {
        return physiotherapistService.deletePhysiotherapist(id);
    }
}
