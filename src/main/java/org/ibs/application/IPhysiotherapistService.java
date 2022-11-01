package org.ibs.application;

import org.ibs.application.dto.physiotherapistdto.GetPhysioPatient;
import org.ibs.application.dto.physiotherapistdto.GetPhysiotherapist;
import org.ibs.application.dto.physiotherapistdto.SavePhysiotherapist;

import java.util.List;

public interface IPhysiotherapistService {
    GetPhysiotherapist getPhysioData(String id) throws Exception;
    List<GetPhysioPatient> getPhysioPatientData(String id) throws Exception;
    GetPhysiotherapist savePhysiotherapist(SavePhysiotherapist physiotherapist) throws Exception;
    GetPhysiotherapist updatePhysiotherapist(GetPhysiotherapist getPhysiotherapist) throws Exception;
    boolean deletePhysiotherapist(String id) throws Exception;
}
