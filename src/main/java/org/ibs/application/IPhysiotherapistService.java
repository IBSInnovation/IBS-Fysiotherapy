package org.ibs.application;

import org.ibs.application.dto.PhysiotherapistDTO;
import org.ibs.domain.Physiotherapist;

import java.util.ArrayList;

public interface IPhysiotherapistService {
    Physiotherapist getById(long id);
    ArrayList<Physiotherapist> getAll();
    Physiotherapist persistPhysiotherapist(PhysiotherapistDTO physiotherapistDTO);
    boolean deletePhysiotherapist(long id);
}
