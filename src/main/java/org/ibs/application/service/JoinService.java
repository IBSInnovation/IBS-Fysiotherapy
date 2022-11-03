package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import lombok.AllArgsConstructor;
import org.ibs.application.IJoinService;
import org.ibs.application.IPhysiotherapistService;
import org.ibs.application.dto.PlaceholderDTO;
import org.ibs.application.dto.pageloaderdto.HomePageData;
import org.ibs.application.dto.physiotherapistdto.GetPhysioPatient;
import org.ibs.application.dto.physiotherapistdto.GetPhysiotherapist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JoinService implements IJoinService {
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

    public static void deleteSubCollections(CollectionReference collection, int batchSize) throws Exception {
        try {
            ApiFuture<QuerySnapshot> future = collection.limit(batchSize).get();
            int deleted = 0;
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                document.getReference().delete();
                ++deleted;
            }
            if (deleted >= 10) {
                deleteSubCollections(collection, batchSize);
            }
        } catch (Exception e) {
            throw new Exception("Subcollection was not deleted due to an error", e);
        }
    }
}
