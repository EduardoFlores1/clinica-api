package com.nolimits.clinica.repositories;

import com.nolimits.clinica.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicoRepo extends JpaRepository<Medico, Long> {
}
