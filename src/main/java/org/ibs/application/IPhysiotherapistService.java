package org.ibs.application;

import org.ibs.application.dto.Physiotherapist.PhysiotherapistDTO;
import org.ibs.domain.Physiotherapist;

import java.util.List;

public interface IPhysiotherapistService {
    Physiotherapist getById(String id) throws Exception;
    List<Physiotherapist> getAll() throws Exception;
    PhysiotherapistDTO persistPhysiotherapist(PhysiotherapistDTO physiotherapist) throws Exception;
    boolean deletePhysiotherapist(String id) throws Exception;
}
