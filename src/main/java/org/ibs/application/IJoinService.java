package org.ibs.application;

import org.ibs.application.dto.PlaceholderDTO;
import org.ibs.application.dto.joindto.ExerciseAndMeasurementData;
import org.ibs.application.dto.joindto.HomePageData;
import org.ibs.application.dto.joindto.PatientPageData;

public interface IJoinService {
    HomePageData getDataForHomePage(String physioId) throws Exception;

    PatientPageData getDataForPatientPage(String patientId) throws Exception;

    ExerciseAndMeasurementData getDataForExercise(String measurementId) throws Exception;

    PlaceholderDTO getDataForStartExercisePage();
}
