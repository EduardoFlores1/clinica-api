package com.nolimits.clinica.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaciente;

    @Size(min = 3, max = 70, message = "nombres -> Min. 3 y Max. 70 caracteres")
    @Column(name = "nombres", nullable = false, length = 70)
    private String nombres;
    @Size(min = 3, max = 70, message = "apellidos -> Min. 3 y Max. 70 caracteres")
    @Column(name = "apellidos", nullable = false, length = 70)
    private String apellidos;
    @Size(min = 8, max = 8, message = "DNI -> 11 caracteres")
    @Column(name = "DNI", nullable = false, length = 8, unique = true)
    private String DNI;
    @Size(min = 3, max = 150, message = "direccion -> Min. 3 y Max. 150 caracteres")
    @Column(name = "direccion", nullable = false, length = 150)
    private String direccion;
    @Size(min = 9, max = 9, message = "telefono -> 9 caracteres")
    @Column(name = "telefono", nullable = false, length = 9, unique = true)
    private String telefono;
    @Email
    @Column(name = "email")
    private String email;

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
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

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
