package com.nolimits.clinica.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ConsultaExamenPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_examen", nullable = false, foreignKey = @ForeignKey(name = "fk_consulta_examen_examen"))
    private Examen examen;

    @ManyToOne
    @JoinColumn(name = "id_consulta", nullable = false, foreignKey = @ForeignKey(name = "fk_consulta_examen_consulta"))
    private Consulta consulta;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsultaExamenPK that = (ConsultaExamenPK) o;
        return Objects.equals(examen, that.examen) && Objects.equals(consulta, that.consulta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examen, consulta);
    }

    @Serial
    private static final long serialVersionUID = -6522481020104240960L;
}
