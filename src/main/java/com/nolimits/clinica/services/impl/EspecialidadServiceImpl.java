package com.nolimits.clinica.services.impl;

import com.nolimits.clinica.models.Especialidad;
import com.nolimits.clinica.repositories.IEspecialidadRepo;
import com.nolimits.clinica.services.IEspecialidadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService {

    private final IEspecialidadRepo repo;

    public EspecialidadServiceImpl(IEspecialidadRepo repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Especialidad> findAll() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Especialidad> findById(Long id) {
        return repo.findById(id);
    }

    @Transactional
    @Override
    public Especialidad save(Especialidad newObj) {
        return repo.save(newObj);
    }

    @Transactional
    @Override
    public Especialidad update(Especialidad updObj) {
        return repo.save(updObj);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
