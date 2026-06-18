package com.proelectricos.mdserp.service.sqlfactory;

import com.proelectricos.mdserp.model.entity.sqlfactory.ViOpFabricur;
import com.proelectricos.mdserp.repository.sqlfactory.ViOpFabricurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ViOpFabricurService {

    private final ViOpFabricurRepository viOpFabricurRepository;

    public List<ViOpFabricur> findAllViOpFabricur() {
        return viOpFabricurRepository.findAll();
    }
}
