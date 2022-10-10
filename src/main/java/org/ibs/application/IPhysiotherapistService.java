package org.ibs.application;

import org.ibs.application.dto.Physiotherapist.SavePhysiotherapist;
import org.ibs.domain.Physiotherapist;

import java.util.List;

public interface IPhysiotherapistService {
    Physiotherapist getById(String id) throws Exception;
    List<Physiotherapist> getAll() throws Exception;
    SavePhysiotherapist savePhysiotherapist(SavePhysiotherapist physiotherapist) throws Exception;
    boolean deletePhysiotherapist(String id) throws Exception;
}
