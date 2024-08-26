package com.nolimits.clinica.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "detalle_consulta")
public class DetalleConsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdDetalleConsulta;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_consulta", nullable = false, foreignKey = @ForeignKey(name = "fk_detalleConsulta_consulta"))
    private Consulta consulta;
    @Size(min = 3, max = 70, message = "diagnostico -> Min. 3 y Max. 70 caracteres")
    @Column(name = "diagnostico", nullable = false, length = 70)
    private String diagnostico;
    @Size(min = 3, max = 300, message = "tratamiento -> Min. 3 y Max. 300 caracteres")
    @Column(name = "tratamiento", nullable = false, length = 300)
    private String tratamiento;

    public Long getIdDetalleConsulta() {
        return IdDetalleConsulta;
    }

    public void setIdDetalleConsulta(Long idDetalleConsulta) {
        IdDetalleConsulta = idDetalleConsulta;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }
}
