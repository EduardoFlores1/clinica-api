package com.nolimits.clinica.services.impl;

import com.nolimits.clinica.models.Medico;
import com.nolimits.clinica.repositories.IMedicoRepo;
import com.nolimits.clinica.services.IMedicoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoServiceImpl implements IMedicoService {

    private final IMedicoRepo repo;

    public MedicoServiceImpl(IMedicoRepo repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Medico> findAll() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Medico> findById(Long id) {
        return repo.findById(id);
    }

    @Transactional
    @Override
    public Medico save(Medico newObj) {
        return repo.save(newObj);
    }

    @Transactional
    @Override
    public Medico update(Medico updObj) {
        return repo.save(updObj);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
