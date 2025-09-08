package com.proelectricos.mdserp.model.entity.pdm;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

@Data
@Entity
@Table (name = "Variable", catalog = "MDS-INGENIERIA", schema = "dbo")
public class Variable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VariableID", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "VariableName", nullable = false)
    private String variableName;

    @Column(name = "VariableType", nullable = false)
    private Integer variableType;

    @Column(name = "IsDeleted", nullable = false)
    private Boolean isDeleted = false;

    @Column(name = "FlagUnique", nullable = false)
    private Boolean flagUnique = false;

    @Column(name = "FlagMandatory", nullable = false)
    private Boolean flagMandatory = false;

    @ColumnDefault("0")
    @Column(name = "FlagFreeUpdateAllVersion", nullable = false)
    private Boolean flagFreeUpdateAllVersion = false;

    @ColumnDefault("0")
    @Column(name = "FlagFreeUpdateLatestVersion", nullable = false)
    private Boolean flagFreeUpdateLatestVersion = false;

    @Override
    public String toString() {
        return "Variable{" +
                "id=" + id +
                ", name='" + variableName +
                '}';


    }

}