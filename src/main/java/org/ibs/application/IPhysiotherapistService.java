package org.ibs.application;

import org.ibs.application.dto.PlaceholderDTO;
import org.ibs.application.dto.physiotherapistdto.GetPhysioPatient;
import org.ibs.application.dto.physiotherapistdto.GetPhysiotherapist;
import org.ibs.application.dto.physiotherapistdto.SavePhysioPatient;
import org.ibs.application.dto.physiotherapistdto.SavePhysiotherapist;

import java.util.List;

public interface IPhysiotherapistService {
    GetPhysiotherapist getPhysioData(String id) throws Exception;
    List<GetPhysioPatient> getPhysioPatientData(String id) throws Exception;
    GetPhysiotherapist savePhysiotherapist(SavePhysiotherapist physiotherapist) throws Exception;

    SavePhysioPatient persistPatientToPhysio(SavePhysioPatient savePhysioPatient);

    boolean deletePhysiotherapist(String id) throws Exception;

    PlaceholderDTO getDataAfterLogin(String id);
}
