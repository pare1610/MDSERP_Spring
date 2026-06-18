package com.proelectricos.mdserp.controller;

import com.proelectricos.mdserp.model.dto.sqlfactory.CoPedidosPendDto;
import com.proelectricos.mdserp.model.entity.sqlfactory.CoPedidosPend;
import com.proelectricos.mdserp.service.sqlfactory.CoPedidosPendService;
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
@RequestMapping("/api/pedidos-pend")
public class CoPedidosPendController {

    private final CoPedidosPendService coPedidosPendService;
    private final ModelMapper mapper;

    @GetMapping
    public List<CoPedidosPendDto> getCoPedidosPend() {
        return coPedidosPendService.findAllCoPedidosPend()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CoPedidosPendDto convertToDto(CoPedidosPend entity) {
        return mapper.map(entity, CoPedidosPendDto.class);
    }
}
