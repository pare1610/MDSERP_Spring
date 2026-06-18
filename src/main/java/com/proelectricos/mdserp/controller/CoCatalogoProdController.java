package com.proelectricos.mdserp.controller;

import com.proelectricos.mdserp.model.dto.sqlfactory.CoCatalogoProdDto;
import com.proelectricos.mdserp.model.entity.sqlfactory.CoCatalogoProd;
import com.proelectricos.mdserp.service.sqlfactory.CoCatalogoProdService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/catalogo-prod")
public class CoCatalogoProdController {

    private final CoCatalogoProdService coCatalogoProdService;
    private final ModelMapper mapper;

    @GetMapping
    public List<CoCatalogoProdDto> getCoCatalogoProd() {
        return coCatalogoProdService.findAllCoCatalogoProd()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CoCatalogoProdDto convertToDto(CoCatalogoProd entity) {
        return mapper.map(entity, CoCatalogoProdDto.class);
    }
}
