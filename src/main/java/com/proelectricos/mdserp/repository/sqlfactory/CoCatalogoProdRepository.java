package com.proelectricos.mdserp.repository.sqlfactory;

import com.proelectricos.mdserp.model.entity.sqlfactory.CoCatalogoProd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoCatalogoProdRepository extends JpaRepository<CoCatalogoProd, String> {
}
