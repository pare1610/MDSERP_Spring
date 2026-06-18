package com.proelectricos.mdserp.service.sqlfactory;

import com.proelectricos.mdserp.model.entity.sqlfactory.CoPagNegocio;
import com.proelectricos.mdserp.repository.sqlfactory.CoPagNegocioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CoPagNegocioService {

    private final CoPagNegocioRepository coPagNegocioRepository;

    public Iterable<CoPagNegocio> findAllCoPagNegocio() {
        return coPagNegocioRepository.findAll();
    }
}
