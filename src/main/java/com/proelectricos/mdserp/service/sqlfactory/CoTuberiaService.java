package com.proelectricos.mdserp.service.sqlfactory;

import com.proelectricos.mdserp.model.entity.sqlfactory.CoTuberia;
import com.proelectricos.mdserp.repository.sqlfactory.CoTuberiaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CoTuberiaService {

    private final CoTuberiaRepository coTuberiaRepository;

    public List<CoTuberia> findAllCoTuberia() {
        return coTuberiaRepository.findAll();
    }
}
