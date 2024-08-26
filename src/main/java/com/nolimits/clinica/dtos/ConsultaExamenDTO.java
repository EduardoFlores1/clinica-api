package com.nolimits.clinica.dtos;

import com.nolimits.clinica.models.Consulta;
import com.nolimits.clinica.models.Examen;

import java.util.List;

public class ConsultaExamenDTO {

    private Consulta consulta;
    private List<Examen> examenList;

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public List<Examen> getExamenList() {
        return examenList;
    }

    public void setExamenList(List<Examen> examenList) {
        this.examenList = examenList;
    }
}
