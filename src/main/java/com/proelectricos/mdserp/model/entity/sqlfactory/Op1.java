package com.proelectricos.mdserp.model.entity.sqlfactory;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "op1", catalog = "emp001_inv", schema = "dbo")
public class Op1 {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tableMaxGenerator")
    @GenericGenerator(name = "tableMaxGenerator", strategy =
            "com.proelectricos.mdserp.model.entity.sqlfactory.generator.Op1IdGenerator")
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "OP")
    private Integer op;

    @Column(name = "COD", length = 20)
    private String cod;

    @Column(name = "CANT", precision = 14, scale = 4)
    private BigDecimal cant;

    @Column(name = "CANT1", precision = 14, scale = 4)
    private BigDecimal cant1;

}