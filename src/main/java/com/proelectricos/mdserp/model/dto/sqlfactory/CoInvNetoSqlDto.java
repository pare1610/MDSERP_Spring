package com.proelectricos.mdserp.model.dto.sqlfactory;

import com.proelectricos.mdserp.model.entity.sqlfactory.CoInvNetoSql;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link CoInvNetoSql}
 */
@Getter
@Setter
public class CoInvNetoSqlDto implements Serializable {

    @Size(max = 20)
    private String cod;
    @Size(max = 100)
    private String nom;
    @Size(max = 2)
    private String ud;
    @Size(max = 2)
    private String grup;
    @NotNull
    private BigDecimal pedido;
    @NotNull
    private BigDecimal proceso;
    @NotNull
    private BigDecimal bod31;
    @NotNull
    private BigDecimal bod32;
    @NotNull
    private BigDecimal bod20;
    @NotNull
    private BigDecimal bod39;
    private BigDecimal smax;
    private BigDecimal smin;
    private BigDecimal unidOp;
    private BigDecimal loteOpt;
    private String modelo;
    private BigDecimal consumoPromedio;
    @Size(max = 50)
    private String negocio;
    @Size(max = 80)
    private String linea;
    @Size(max = 50)
    private String sublinea;
    @Size(max = 60)
    private String grupo;
}
