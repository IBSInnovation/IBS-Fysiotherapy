package org.ibs.application.service;

import lombok.AllArgsConstructor;
import org.ibs.application.IPageLoaderService;
import org.ibs.application.IPhysiotherapistService;
import org.ibs.application.dto.PlaceholderDTO;
import org.ibs.application.dto.pageloaderdto.HomePageData;
import org.ibs.application.dto.physiotherapistdto.GetPhysioPatient;
import org.ibs.application.dto.physiotherapistdto.GetPhysiotherapist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PageLoaderService implements IPageLoaderService {
    private final IPhysiotherapistService physiotherapistService;

    @Override
    public HomePageData getDataForHomePage(String physioId) throws Exception {
        GetPhysiotherapist physioDTO = physiotherapistService.getPhysioData(physioId);
        List<GetPhysioPatient> patientDTO = physiotherapistService.getPhysioPatientData(physioId);
        return new HomePageData(physioDTO.id, physioDTO.email, physioDTO.name, patientDTO);
    }

    @Override
    public PlaceholderDTO getDataForPatientPage(String patientId) {
//        haal patient gegevens op
//        haal alle oefeningen namen van de patient op
//        haal alle meting ids van de oefeningen op
//        haal algemene progressie data op
//        return gegevens
        return null;
    }

    @Override
    public PlaceholderDTO getDataForExercisePage(String measurementId) {
//        haal gemeten data op
//        haal datum van meting op
//        haal oefening gegevens op
//        return gegevens
        return null;
    }

    @Override
    public PlaceholderDTO getDataForStartExercisePage() {
        return null;
    }
}
