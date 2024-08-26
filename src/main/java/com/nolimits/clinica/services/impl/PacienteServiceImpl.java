package com.nolimits.clinica.services.impl;

import com.nolimits.clinica.models.Paciente;
import com.nolimits.clinica.repositories.IPacienteRepo;
import com.nolimits.clinica.services.IPacienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements IPacienteService {

    private final IPacienteRepo repo;

    public PacienteServiceImpl(IPacienteRepo repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Paciente> findAll() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Paciente> findById(Long id) {
        return repo.findById(id);
    }

    @Transactional
    @Override
    public Paciente save(Paciente newObj) {
        return repo.save(newObj);
    }

    @Transactional
    @Override
    public Paciente update(Paciente updObj) {
        return repo.save(updObj);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
