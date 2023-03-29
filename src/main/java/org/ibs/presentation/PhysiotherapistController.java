package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IPhysiotherapistService;
import org.ibs.application.dto.physiotherapistdto.GetPhysiotherapist;
import org.ibs.application.dto.physiotherapistdto.SavePhysiotherapist;
import org.ibs.application.service.PhysiotherapistService;
import org.ibs.domain.Physiotherapist;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/physiotherapist")
@AllArgsConstructor
public class PhysiotherapistController {
    private final PhysiotherapistService physiotherapistService;

    @GetMapping("/{id}")
    public GetPhysiotherapist getPhysiotherapistById(@PathVariable String id) throws Exception {
        return physiotherapistService.getById(id);
    }

    @GetMapping
    public List<GetPhysiotherapist> getAllPhysiotherapists() throws Exception {
        return physiotherapistService.getAll();
    }

    @PostMapping
    public Physiotherapist createPhysiotherapist(@RequestBody SavePhysiotherapist savePhysiotherapist) throws Exception {
        return physiotherapistService.savePhysiotherapist(savePhysiotherapist);
    }

    @PatchMapping("/{id}")
    public Physiotherapist updatePhysiotherapist(@PathVariable String id, @RequestBody SavePhysiotherapist savePhysiotherapist) throws Exception {
        return physiotherapistService.updatePhysiotherapist(id, savePhysiotherapist);
    }

    @DeleteMapping("/{id}")
    public boolean deletePhysiotherapist(@PathVariable String id) throws Exception {
        return physiotherapistService.deletePhysiotherapist(id);
    }
}
