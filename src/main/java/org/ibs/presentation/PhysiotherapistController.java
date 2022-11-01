package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IPhysiotherapistService;
import org.ibs.application.dto.PlaceholderDTO;
import org.ibs.application.dto.physiotherapistdto.GetPhysiotherapist;
import org.ibs.application.dto.physiotherapistdto.SavePhysioPatient;
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
    public GetPhysiotherapist createPhysiotherapist(@RequestBody SavePhysiotherapist savePhysiotherapist) throws Exception {
        return physiotherapistService.savePhysiotherapist(savePhysiotherapist);
    }

//    fix naamgeving enzo
    @PostMapping("/patient")
    public SavePhysioPatient blalba(@RequestBody SavePhysioPatient savePhysioPatient) {
        return physiotherapistService.savePatientToPhysio(savePhysioPatient);
    }

    @PatchMapping
    public GetPhysiotherapist updatePhysiotherapist(@RequestBody GetPhysiotherapist getPhysiotherapist) throws Exception {
        return physiotherapistService.updatePhysiotherapist(getPhysiotherapist);
    }

    @DeleteMapping("/{id}")
    public boolean deletePhysiotherapist(@PathVariable String id) throws Exception {
        return physiotherapistService.deletePhysiotherapist(id);
    }

    @GetMapping("/data/{id}")
    public PlaceholderDTO getDataAfterLogin(@PathVariable String id) {
//        vraag aan pageloader om home pagina data te halen
        return null;
    }
}
