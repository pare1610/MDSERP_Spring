package com.proelectricos.mdserp.service.sqlfactory;

import com.proelectricos.mdserp.model.entity.sqlfactory.Adic;
import com.proelectricos.mdserp.repository.sqlfactory.AdicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AdicService {

    private final AdicRepository adicRepository;

  public Iterable<Adic> findAllAdics() {
        return adicRepository.findAll();
    }

}
