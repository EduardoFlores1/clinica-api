package com.nolimits.clinica.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "consulta")
public class Consulta {

    public Consulta() {

    }

    public Consulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsulta;

    @ManyToOne
    @JoinColumn(name = "id_medico", nullable = false, foreignKey = @ForeignKey(name = "fk_consulta_medico"))
    private Medico medico;
    @ManyToOne
    @JoinColumn(name = "id_especialidad", nullable = false, foreignKey = @ForeignKey(name = "fk_consulta_especialidad"))
    private Especialidad especialidad;
    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false, foreignKey = @ForeignKey(name = "fk_consulta_paciente"))
    private Paciente paciente;

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime dateTime;

    @OneToMany(mappedBy = "consulta", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}
                , fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DetalleConsulta> detalleConsultaList;

    public Long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<DetalleConsulta> getDetalleConsultaList() {
        return detalleConsultaList;
    }

    public void setDetalleConsultaList(List<DetalleConsulta> detalleConsultaList) {
        this.detalleConsultaList = detalleConsultaList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consulta consulta = (Consulta) o;
        return Objects.equals(idConsulta, consulta.idConsulta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConsulta);
    }
}
