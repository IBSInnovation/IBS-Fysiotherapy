package org.ibs.application;

import org.ibs.application.dto.PlaceholderDTO;
import org.ibs.application.dto.pageloaderdto.HomePageData;

public interface IJoinService {
    HomePageData getDataForHomePage(String physioId) throws Exception;

    PlaceholderDTO getDataForPatientPage(String patientId);

    PlaceholderDTO getDataForExercisePage(String measurementId);

    PlaceholderDTO getDataForStartExercisePage();
}
