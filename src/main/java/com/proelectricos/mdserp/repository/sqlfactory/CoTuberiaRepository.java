package com.proelectricos.mdserp.repository.sqlfactory;

import com.proelectricos.mdserp.model.entity.sqlfactory.CoTuberia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoTuberiaRepository extends JpaRepository<CoTuberia, String> {
}
