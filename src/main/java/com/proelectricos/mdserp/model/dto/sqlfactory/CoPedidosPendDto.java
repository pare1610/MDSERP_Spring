package com.proelectricos.mdserp.model.dto.sqlfactory;

import com.proelectricos.mdserp.model.entity.sqlfactory.CoPedidosPend;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * DTO for {@link CoPedidosPend}
 */
@Getter
@Setter
public class CoPedidosPendDto implements Serializable {

    //private Long id;

    private LocalDate fecha;

    @Size(max = 7)
    private String num;
    @Size(max = 15)
    private String cliente;
    @Size(max = 120)
    private String nomCliente;
    @Size(max = 5)
    private String codVend;
    @Size(max = 30)
    private String nomVend;
    @Size(max = 20)
    private String ciu;
    @Size(max = 3)
    private String zona;
    private LocalDate entrega;
    @Size(max = 20)
    private String cod;
    @Size(max = 200)
    private String nom;
    @Size(max = 2)
    private String ud;
    @Size(max = 2)
    private String grup;
    private BigDecimal cant;
    private BigDecimal costo;
    private BigDecimal pend;
    private Character estado;
    private BigDecimal neto;
    @Size(max = 50)
    private String orden;
    @Size(max = 6)
    private String ncod;
    @Size(max = 30)
    private String condic;
    private BigDecimal despacho;
    private BigDecimal cantPend;
    private BigDecimal vrPendiente;
    @Size(max = 30)
    private String negocio;
    @Size(max = 50)
    private String clase;
    @Size(max = 80)
    private String linea;
    @Size(max = 50)
    private String grupo;
    @Size(max = 60)
    private String nivel5;
}
