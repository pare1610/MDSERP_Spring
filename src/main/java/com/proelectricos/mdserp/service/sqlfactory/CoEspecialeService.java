package com.proelectricos.mdserp.service.sqlfactory;

import com.proelectricos.mdserp.model.entity.sqlfactory.CoEspeciale;
import com.proelectricos.mdserp.repository.sqlfactory.CoEspecialeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CoEspecialeService {

    private final CoEspecialeRepository coEspecialeRepository;

    public List<CoEspeciale> findAllCoEspeciales() {
        return coEspecialeRepository.findAll();
    }
}
