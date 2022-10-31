package org.ibs.presentation;

import lombok.AllArgsConstructor;
import org.ibs.application.IPhysiotherapistService;
import org.ibs.application.dto.PlaceholderDTO;
import org.ibs.application.dto.physiotherapistdto.GetPhysiotherapist;
import org.ibs.application.dto.physiotherapistdto.SavePhysiotherapist;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/physiotherapist")
@AllArgsConstructor
public class PhysiotherapistController {
    private final IPhysiotherapistService physiotherapistService;

    @GetMapping("/{id}")
    public GetPhysiotherapist getPhysiotherapistById(@PathVariable String id) throws Exception {
        return physiotherapistService.getPhysioData(id);
    }

//    TODO: kan verwijderd worden
    @GetMapping
    public List<GetPhysiotherapist> getAllPhysiotherapists() throws Exception {
        return physiotherapistService.getAll();
    }

    @PostMapping
    public SavePhysiotherapist createPhysiotherapist(@RequestBody SavePhysiotherapist savePhysiotherapist) throws Exception {
        return physiotherapistService.savePhysiotherapist(savePhysiotherapist);
    }

    @PatchMapping
    public SavePhysiotherapist updatePhysiotherapist(@RequestBody SavePhysiotherapist savePhysiotherapist) throws Exception {
        return physiotherapistService.savePhysiotherapist(savePhysiotherapist);
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
