package com.proelectricos.mdserp.repository.sqlfactory;

import com.proelectricos.mdserp.model.entity.sqlfactory.ViOpFabricur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViOpFabricurRepository extends JpaRepository<ViOpFabricur, Integer> {
}
