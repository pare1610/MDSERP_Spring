package com.proelectricos.mdserp.service.sqlfactory;

import com.proelectricos.mdserp.model.entity.sqlfactory.CoOpProceso;
import com.proelectricos.mdserp.repository.sqlfactory.CoOpProcesoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CoOpProcesoService {

    private final CoOpProcesoRepository repository;

    public List<CoOpProceso> findAll() {
        return repository.findAll();
    }
}
