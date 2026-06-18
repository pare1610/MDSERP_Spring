package com.proelectricos.mdserp.controller;

import com.proelectricos.mdserp.model.dto.sqlfactory.CoOpProcesoDto;
import com.proelectricos.mdserp.model.entity.sqlfactory.CoOpProceso;
import com.proelectricos.mdserp.service.sqlfactory.CoOpProcesoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/co-op-proceso")
public class CoOpProcesoController {

    private final CoOpProcesoService service;
    private final ModelMapper mapper;

    @GetMapping
    public List<CoOpProcesoDto> getAll() {
        return service.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CoOpProcesoDto convertToDto(CoOpProceso entity) {
        return mapper.map(entity, CoOpProcesoDto.class);
    }
}
