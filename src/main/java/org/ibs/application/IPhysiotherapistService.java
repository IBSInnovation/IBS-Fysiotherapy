package org.ibs.application;

import org.ibs.application.dto.PhysiotherapistDTO;
import org.ibs.domain.Physiotherapist;

import java.util.List;

public interface IPhysiotherapistService {
    Physiotherapist getById(long id);
    List<Physiotherapist> getAll();
    Physiotherapist persistPhysiotherapist(PhysiotherapistDTO physiotherapistDTO);
    boolean deletePhysiotherapist(long id);
}
