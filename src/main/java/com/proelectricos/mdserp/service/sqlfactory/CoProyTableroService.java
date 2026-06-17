package com.proelectricos.mdserp.service.sqlfactory;

import com.proelectricos.mdserp.model.entity.sqlfactory.Adic;
import com.proelectricos.mdserp.model.entity.sqlfactory.CoProyTablero;
import com.proelectricos.mdserp.repository.sqlfactory.CoProyTableroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CoProyTableroService {
    private final CoProyTableroRepository CoProyTableroRepository;

    public Iterable<CoProyTablero> findAllCoProyTablero() {
        return CoProyTableroRepository.findAll();
    }
}
