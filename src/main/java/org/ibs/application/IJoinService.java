package org.ibs.application;

import org.ibs.application.dto.PlaceholderDTO;
import org.ibs.application.dto.categorydto.GetCategory;
import org.ibs.application.dto.exercisedto.GetExercise;
import org.ibs.application.dto.joindto.ExerciseAndMeasurementData;
import org.ibs.application.dto.joindto.HomePageData;
import org.ibs.application.dto.joindto.PatientPageData;
import org.ibs.application.dto.patientdto.GetPatient;

public interface IJoinService {
    HomePageData getDataForHomePage(String physioId) throws Exception;

    PatientPageData getDataForPatientPage(String patientId) throws Exception;

    ExerciseAndMeasurementData getDataForMeasurement(String measurementId) throws Exception;

    PlaceholderDTO getDataForStartExercisePage();

    PlaceholderDTO updateCategory(GetCategory getCategory) throws Exception;

    boolean deleteCategoryAndSubcollections(String CategoryId) throws Exception;

    PlaceholderDTO updateExercise(GetExercise getExercise) throws Exception;

    boolean deleteExerciseAndSubcollections(String ExerciseId) throws Exception;

    PlaceholderDTO updatePatient(GetPatient getPatient) throws Exception;

    boolean deletePatientAndSubcollections(String id) throws Exception;
}
