package org.ibs.application.service;

import lombok.AllArgsConstructor;
import org.ibs.application.IPhysiotherapistService;
import org.ibs.application.dto.PhysiotherapistDTO;
import org.ibs.domain.Physiotherapist;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PhysiotherapistService implements IPhysiotherapistService {
    @Override
    public Physiotherapist getById(long id) {
        return null;
    }

    @Override
    public List<Physiotherapist> getAll() {
        return null;
    }

    @Override
    public Physiotherapist persistPhysiotherapist(PhysiotherapistDTO physiotherapistDTO) {
        return null;
    }

    @Override
    public boolean deletePhysiotherapist(long id) {
        return false;
    }
}
