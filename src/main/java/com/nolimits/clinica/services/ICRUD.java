package com.nolimits.clinica.services;

import java.util.List;
import java.util.Optional;

public interface ICRUD <T>{

    List<T> findAll();
    Optional<T> findById(Long id);
    T save(T newObj);
    T update(T updObj);
    void delete(Long id);

}
