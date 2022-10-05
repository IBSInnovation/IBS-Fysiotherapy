package org.ibs.application;

import org.ibs.domain.Physiotherapist;

import java.util.List;

public interface IPhysiotherapistService {
    Physiotherapist getById(String id) throws Exception;
    List<Physiotherapist> getAll() throws Exception;
    Physiotherapist persistPhysiotherapist(Physiotherapist physiotherapist) throws Exception;
    boolean deletePhysiotherapist(long id) throws Exception;
}
