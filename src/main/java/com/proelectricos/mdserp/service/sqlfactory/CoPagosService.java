package com.proelectricos.mdserp.service.sqlfactory;

import com.proelectricos.mdserp.model.entity.sqlfactory.CoPagos;
import com.proelectricos.mdserp.repository.sqlfactory.CoPagosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CoPagosService {

    private final CoPagosRepository CoPagosRepository;

    public Iterable<CoPagos> findAllCoPagos() {
        return CoPagosRepository.findAll();
    }
}
