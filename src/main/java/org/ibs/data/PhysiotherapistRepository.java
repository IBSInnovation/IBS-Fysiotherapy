package org.ibs.data;

import org.ibs.domain.Category;
import org.ibs.domain.Physiotherapist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysiotherapistRepository extends JpaRepository<Physiotherapist, Long> {
}
