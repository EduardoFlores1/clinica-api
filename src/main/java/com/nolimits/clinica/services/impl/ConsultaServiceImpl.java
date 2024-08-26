package com.nolimits.clinica.services.impl;

import com.nolimits.clinica.dtos.ConsultaExamenDTO;
import com.nolimits.clinica.models.Consulta;
import com.nolimits.clinica.models.ConsultaExamen;
import com.nolimits.clinica.repositories.IConsultaExamenRepo;
import com.nolimits.clinica.repositories.IConsultaRepo;
import com.nolimits.clinica.services.IConsultaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaServiceImpl implements IConsultaService {

    private final IConsultaRepo repo;
    private final IConsultaExamenRepo ceRepo;

    public ConsultaServiceImpl(IConsultaRepo repo, IConsultaExamenRepo ceRepo) {
        this.repo = repo;
        this.ceRepo = ceRepo;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Consulta> findAll() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Consulta> findById(Long id) {
        return repo.findById(id);
    }

    // Se guarda la referencia del padre(consulta) en cada detalle
    @Transactional
    @Override
    public Consulta save(Consulta newObj) {
        newObj.getDetalleConsultaList().forEach(det -> det.setConsulta(newObj));
        return repo.save(newObj);
    }

    @Transactional
    @Override
    public Consulta update(Consulta updObj) {
        return repo.save(updObj);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Transactional
    @Override
    public Consulta registroTransactional(ConsultaExamenDTO consultaExamenDTO) {

        // Se guarda la referencia del padre(consulta) en cada detalle
        consultaExamenDTO.getConsulta().getDetalleConsultaList().forEach(det -> det.setConsulta(consultaExamenDTO.getConsulta()));

        // Se guarda la consulta
        repo.save(consultaExamenDTO.getConsulta());

        // Para cada examen de ExamenList, se registra idConsulta e idExamen
        // en la entidad ConsultaExamen para guardarse una por una
        consultaExamenDTO.getExamenList().forEach(examen -> ceRepo.registrarCE(consultaExamenDTO.getConsulta().getIdConsulta(), examen.getIdExamen()));
        return consultaExamenDTO.getConsulta();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ConsultaExamen> withIdConsulta(List<Consulta> consulta) {
        return ceRepo.findByConsultaIn(consulta);
    }
}
