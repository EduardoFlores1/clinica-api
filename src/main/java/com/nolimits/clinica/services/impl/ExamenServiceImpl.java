package com.nolimits.clinica.services.impl;

import com.nolimits.clinica.models.Examen;
import com.nolimits.clinica.repositories.IExamenRepo;
import com.nolimits.clinica.services.IExamenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExamenServiceImpl implements IExamenService {

    private final IExamenRepo repo;

    public ExamenServiceImpl(IExamenRepo repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Examen> findAll() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Examen> findById(Long id) {
        return repo.findById(id);
    }

    @Transactional
    @Override
    public Examen save(Examen newObj) {
        return repo.save(newObj);
    }

    @Transactional
    @Override
    public Examen update(Examen updObj) {
        return repo.save(updObj);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
