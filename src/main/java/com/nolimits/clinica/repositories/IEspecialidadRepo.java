package com.nolimits.clinica.repositories;

import com.nolimits.clinica.models.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEspecialidadRepo extends JpaRepository<Especialidad, Long> {
}
