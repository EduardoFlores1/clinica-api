package com.nolimits.clinica.repositories;

import com.nolimits.clinica.models.Consulta;
import com.nolimits.clinica.models.ConsultaExamen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IConsultaExamenRepo extends JpaRepository<ConsultaExamen, Long> {

    @Modifying
    @Query(value = "INSERT INTO consulta_examen(id_consulta, id_examen) values(:idConsulta, :idExamen)", nativeQuery = true)
    void registrarCE(@Param("idConsulta") Long idConsulta, @Param("idExamen") Long idExamen);

    // Buscamos todas las ConsultaExamen que tengan el mismo id de una consulta
    // El método las relaciones según la lista ingresada
    List<ConsultaExamen> findByConsultaIn(List<Consulta> consulta);
}
