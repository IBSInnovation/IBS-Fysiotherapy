package org.ibs.application.dto.pageloaderdto;

import lombok.AllArgsConstructor;
import org.ibs.application.dto.physiotherapistdto.GetPhysioPatient;

import java.util.List;

@AllArgsConstructor
public class HomePageData {
    public String physioId;
    public String physioEmail;
    public String physioName;
    public List<GetPhysioPatient> patientList;
}
