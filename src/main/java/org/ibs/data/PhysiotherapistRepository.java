package org.ibs.data;

import org.ibs.domain.Physiotherapist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhysiotherapistRepository extends JpaRepository<Physiotherapist, Long> {
}
