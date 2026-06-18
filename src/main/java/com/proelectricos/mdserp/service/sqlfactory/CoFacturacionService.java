package com.proelectricos.mdserp.service.sqlfactory;


import com.proelectricos.mdserp.model.entity.sqlfactory.CoFacturacion;
import com.proelectricos.mdserp.repository.sqlfactory.CoFacturacionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CoFacturacionService {

    private final CoFacturacionRepository CoFacturacionRepository;

    public Iterable<CoFacturacion> findAllCoFacturacion() {
        return CoFacturacionRepository.findAll();
    }
}
