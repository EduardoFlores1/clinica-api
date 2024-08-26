package com.nolimits.clinica.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedico;

    @Size(min = 3, max = 70, message = "nombres -> Min. 3 y Max. 70 caracteres")
    @Column(name = "nombres", nullable = false, length = 70)
    private String nombres;
    @Size(min = 3, max = 70, message = "apellidos -> Min. 3 y Max. 70 caracteres")
    @Column(name = "apellidos", nullable = false, length = 70)
    private String apellidos;
    @Size(min = 12, max = 12, message = "CMP debe tener 12 caracteres")
    @Column(name = "CMP", nullable = false, length = 12, unique = true)
    private String CMP;

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCMP() {
        return CMP;
    }

    public void setCMP(String CMP) {
        this.CMP = CMP;
    }
}
