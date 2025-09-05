package com.proelectricos.mdserp.model.dto;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * DTO for {@link com.proelectricos.mdserp.model.entity.Adic}
 */
@Value
public class AdicDto implements Serializable {
    Integer id;
    String cod;
    Integer op;
    String nom;
    BigDecimal valor;
    BigDecimal cant;
    LocalDate fecha;
}