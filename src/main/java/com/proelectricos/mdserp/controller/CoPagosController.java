package com.proelectricos.mdserp.controller;


import com.proelectricos.mdserp.model.dto.sqlfactory.CoPagosDto;
import com.proelectricos.mdserp.model.entity.sqlfactory.CoPagos;
import com.proelectricos.mdserp.service.sqlfactory.CoPagosService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@RestController
@RequestMapping("/api/pagos")
class CoPagosController {
    private final CoPagosService CoPagosService;
    private final ModelMapper mapper;

    @GetMapping
    public List<CoPagosDto> getCoPagos() {
        var CoPagosList = StreamSupport
                .stream(CoPagosService.findAllCoPagos().spliterator(), false)
                .collect(Collectors.toList());

        return CoPagosList
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CoPagosDto convertToDto(CoPagos entity) {
        return mapper.map(entity, CoPagosDto.class);
    }

}
