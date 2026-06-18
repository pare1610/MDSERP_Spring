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
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Entity
@Immutable
@Table(name = "CO_PEDIDOS_PEND",  catalog = "EMP001_FACT", schema = "dbo")
public class CoPedidosPend {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "FECHA")
    private LocalDate fecha;



    @Size(max = 7)
    @Column(name = "NUM", length = 7)
    private String num;

    @Size(max = 15)
    @Column(name = "CLIENTE", length = 15)
    private String cliente;

    @Size(max = 120)
    @Column(name = "NomCliente", length = 120)
    private String nomCliente;

    @Size(max = 5)
    @Column(name = "CodVend", length = 5)
    private String codVend;

    @Size(max = 30)
    @Column(name = "NomVend", length = 30)
    private String nomVend;

    @Size(max = 20)
    @Column(name = "CIU", length = 20)
    private String ciu;

    @Size(max = 3)
    @Column(name = "ZONA", length = 3)
    private String zona;

    @Column(name = "ENTREGA")
    private LocalDate entrega;


    @Size(max = 20)
    @Column(name = "COD", length = 20)
    private String cod;

    @Size(max = 200)
    @Column(name = "NOM", length = 200)
    private String nom;

    @Size(max = 2)
    @Column(name = "UD", length = 2)
    private String ud;

    @Size(max = 2)
    @Column(name = "GRUP", length = 2)
    private String grup;

    @Column(name = "CANT", precision = 14, scale = 4)
    private BigDecimal cant;

    @Column(name = "COSTO", precision = 16, scale = 4)
    private BigDecimal costo;

    @Column(name = "PEND", precision = 14, scale = 4)
    private BigDecimal pend;

    @Column(name = "ESTADO")
    private Character estado;

    @Column(name = "NETO", precision = 16, scale = 4)
    private BigDecimal neto;

    @Size(max = 50)
    @Column(name = "ORDEN", length = 50)
    private String orden;

    @Size(max = 6)
    @Column(name = "NCOD", length = 6)
    private String ncod;

    @Size(max = 30)
    @Column(name = "CONDIC", length = 30)
    private String condic;

    @NotNull
    @Column(name = "DESPACHO", nullable = false, precision = 38, scale = 4)
    private BigDecimal despacho;

    @Column(name = "CANTPEND", precision = 38, scale = 4)
    private BigDecimal cantpend;

    @Column(name = "\"VR PENDIENTE\"", precision = 38, scale = 6)
    private BigDecimal vrPendiente;

    @Size(max = 30)
    @Nationalized
    @Column(name = "Negocio", length = 30)
    private String negocio;

    @Size(max = 50)
    @Nationalized
    @Column(name = "Clase", length = 50)
    private String clase;

    @Size(max = 80)
    @Nationalized
    @Column(name = "Linea", length = 80)
    private String linea;

    @Size(max = 50)
    @Nationalized
    @Column(name = "Grupo", length = 50)
    private String grupo;

    @Size(max = 60)
    @Nationalized
    @Column(name = "Nivel5", length = 60)
    private String nivel5;


}