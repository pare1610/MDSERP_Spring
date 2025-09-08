package com.proelectricos.mdserp.repository.pdm;

import com.proelectricos.mdserp.model.entity.pdm.Variable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariableRepository extends JpaRepository<Variable, Integer> {
}