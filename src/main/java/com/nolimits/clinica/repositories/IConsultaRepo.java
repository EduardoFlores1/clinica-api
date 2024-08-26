package com.nolimits.clinica.repositories;

import com.nolimits.clinica.models.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsultaRepo extends JpaRepository<Consulta, Long> {
}
