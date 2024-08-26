package com.nolimits.clinica.repositories;

import com.nolimits.clinica.models.Examen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExamenRepo extends JpaRepository<Examen, Long> {
}
