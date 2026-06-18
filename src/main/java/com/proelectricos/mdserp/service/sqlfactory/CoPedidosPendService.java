package com.proelectricos.mdserp.service.sqlfactory;

import com.proelectricos.mdserp.model.entity.sqlfactory.CoPedidosPend;
import com.proelectricos.mdserp.repository.sqlfactory.CoPedidosPendRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CoPedidosPendService {

    private final CoPedidosPendRepository coPedidosPendRepository;

    public List<CoPedidosPend> findAllCoPedidosPend() {
        return coPedidosPendRepository.findAll();
    }
}
