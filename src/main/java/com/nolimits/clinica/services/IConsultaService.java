package com.nolimits.clinica.services;

import com.nolimits.clinica.dtos.ConsultaExamenDTO;
import com.nolimits.clinica.models.Consulta;
import com.nolimits.clinica.models.ConsultaExamen;

import java.util.List;

public interface IConsultaService extends ICRUD<Consulta>{

    Consulta registroTransactional(ConsultaExamenDTO consultaExamenDTO);
    List<ConsultaExamen> withIdConsulta(List<Consulta> consulta);
}
