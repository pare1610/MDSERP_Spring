package com.proelectricos.mdserp.model.entity.sqlfactory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

@Getter
@Entity
@Immutable
@Table(name = "CO_INV_NETO_SQL" ,  catalog = "EMP001_INV", schema = "dbo")
public class CoInvNetoSql {

    @Id
    @Size(max = 20)
    @Column(name = "COD", length = 20)
    private String cod;

    @Size(max = 100)
    @Column(name = "NOM", length = 100)
    private String nom;

    @Size(max = 2)
    @Column(name = "UD", length = 2)
    private String ud;

    @Size(max = 2)
    @Column(name = "GRUP", length = 2)
    private String grup;

    @NotNull
    @Column(name = "PEDIDO", nullable = false, precision = 38, scale = 4)
    private BigDecimal pedido;

    @NotNull
    @Column(name = "PROCESO", nullable = false, precision = 38, scale = 4)
    private BigDecimal proceso;

    @NotNull
    @Column(name = "BOD31", nullable = false, precision = 38, scale = 4)
    private BigDecimal bod31;

    @NotNull
    @Column(name = "BOD32", nullable = false, precision = 38, scale = 4)
    private BigDecimal bod32;

    @NotNull
    @Column(name = "BOD20", nullable = false, precision = 38, scale = 4)
    private BigDecimal bod20;

    @NotNull
    @Column(name = "BOD39", nullable = false, precision = 38, scale = 4)
    private BigDecimal bod39;

    @Column(name = "SMAX", precision = 14, scale = 4)
    private BigDecimal smax;

    @Column(name = "SMIN", precision = 14, scale = 4)
    private BigDecimal smin;

    @Column(name = "UNID_OP", precision = 38, scale = 4)
    private BigDecimal unidOp;

    @Column(name = "LOTE_OPT", precision = 16, scale = 4)
    private BigDecimal loteOpt;

    @Column(name = "MODELO", length = 1)
    private String modelo;

    @Column(name = "Consumo Promedio", precision = 16, scale = 4)
    private BigDecimal consumoPromedio;

    @Size(max = 50)
    @Nationalized
    @Column(name = "Negocio", length = 50)
    private String negocio;

    @Size(max = 80)
    @Nationalized
    @Column(name = "LINEA", length = 80)
    private String linea;

    @Size(max = 50)
    @Nationalized
    @Column(name = "SUBLINEA", length = 50)
    private String sublinea;

    @Size(max = 60)
    @Nationalized
    @Column(name = "GRUPO", length = 60)
    private String grupo;


}