package org.ibs.application.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import lombok.AllArgsConstructor;
import org.ibs.application.*;
import org.ibs.application.dto.PlaceholderDTO;
import org.ibs.application.dto.categorydto.GetCategory;
import org.ibs.application.dto.exercisedto.GetExercise;
import org.ibs.application.dto.joindto.ExerciseAndMeasurementData;
import org.ibs.application.dto.joindto.HomePageData;
import org.ibs.application.dto.joindto.PatientPageData;
import org.ibs.application.dto.measurementdto.GetMeasurement;
import org.ibs.application.dto.patientdto.GetPatient;
import org.ibs.application.dto.patientdto.GetPatientMeasurementData;
import org.ibs.application.dto.physiotherapistdto.GetPhysioPatient;
import org.ibs.application.dto.physiotherapistdto.GetPhysiotherapist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JoinService implements IJoinService {
    private final IPhysiotherapistService physiotherapistService;
    private final IPatientService patientService;
    private final IMeasurementService measurementService;
    private final IExerciseService exerciseService;

    @Override
    public HomePageData getDataForHomePage(String physioId) throws Exception {
        GetPhysiotherapist physioDTO = physiotherapistService.getPhysioData(physioId);
        List<GetPhysioPatient> patientDTO = physiotherapistService.getPhysioPatientData(physioId);
        return new HomePageData(physioDTO.id, physioDTO.email, physioDTO.name, patientDTO);
    }

    @Override
    public PatientPageData getDataForPatientPage(String patientId) throws Exception {
        GetPatient patientDTO = patientService.getPatientData(patientId);
        List<GetPatientMeasurementData> measurementIdDTO = patientService.getPatientMeasurementData(patientId);
//        TODO: haal algemene progressie data op
        return new PatientPageData(patientDTO.id,
                patientDTO.name,
                patientDTO.surName,
                patientDTO.weight,
                patientDTO.dateOfBirth,
                patientDTO.email,
                measurementIdDTO);
    }

    @Override
    public ExerciseAndMeasurementData getDataForMeasurement(String measurementId) throws Exception {
        GetMeasurement measurementDTO = measurementService.getMeasurementData(measurementId);
        GetExercise exerciseDTO = exerciseService.getExerciseData(measurementDTO.exercise);
        return new ExerciseAndMeasurementData(measurementDTO.id,
                measurementDTO.dateOfMeasurement,
                measurementDTO.data,
                exerciseDTO.id,
                exerciseDTO.name,
                exerciseDTO.description,
                exerciseDTO.categoryId);
    }

    @Override
    public PlaceholderDTO getDataForStartExercisePage() {
        return null;
    }

    @Override
    public PlaceholderDTO updateCategory(GetCategory getCategory) {
//        pak all exercises van de category die je wilt update
//        update de category referentie in die exercises
//        update de category
        return null;
    }

    @Override
    public boolean deleteCategoryAndSubcollections(String CategoryId) {
//        verwijder alle exercises die gelinkt staan aan een category
//        verwijder alle categorien
        return true;
    }

    @Override
    public PlaceholderDTO updateExercise(GetExercise getExercise) {
//        pak de categorie van de exercise die je wilt updaten
//        update de exercise in de subcolelcite van die category
//        update de exercise
        return null;
    }

    @Override
    public boolean deleteExerciseAndSubcollections(String ExerciseId) {
//        verwijder alle cate die gelinkt staan aan een category
//        verwijder alle categorien
        return true;
    }

    @Override
    public PlaceholderDTO updatePatient(GetPatient getPatient) {
//        update de patient gegevens in physio eerst
//        update patient
        return null;
    }

    @Override
    public boolean deletePatientAndSubcollections(String id) {
//        delete patient uit de physio subcollectie
//        delete patient
        return true;
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
