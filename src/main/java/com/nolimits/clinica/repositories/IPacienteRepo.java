package com.nolimits.clinica.repositories;

import com.nolimits.clinica.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepo extends JpaRepository<Paciente, Long> {
}
