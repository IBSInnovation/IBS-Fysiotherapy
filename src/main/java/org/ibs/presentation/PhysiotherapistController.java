package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IPhysiotherapistService;
import org.ibs.application.dto.physiotherapistdto.GetPhysiotherapist;
import org.ibs.application.dto.physiotherapistdto.SavePhysiotherapist;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/physiotherapist")
@AllArgsConstructor
public class PhysiotherapistController {
    private final IPhysiotherapistService physiotherapistService;

    @GetMapping("/{id}")
    public GetPhysiotherapist getPhysiotherapistById(@PathVariable String id) throws Exception {
        return physiotherapistService.getPhysioData(id);
    }

    @PostMapping
    public GetPhysiotherapist savePhysiotherapist(@RequestBody SavePhysiotherapist savePhysiotherapist) throws Exception {
        return physiotherapistService.savePhysiotherapist(savePhysiotherapist);
    }

    @PatchMapping
    public GetPhysiotherapist updatePhysiotherapist(@RequestBody GetPhysiotherapist getPhysiotherapist) throws Exception {
        return physiotherapistService.updatePhysiotherapist(getPhysiotherapist);
    }

    @DeleteMapping("/{id}")
    public boolean deletePhysiotherapist(@PathVariable String id) throws Exception {
        return physiotherapistService.deletePhysiotherapist(id);
    }
}
