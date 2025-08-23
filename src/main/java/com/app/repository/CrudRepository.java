package com.app.repository;

import com.app.model.base.Vehicle;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<ID, T> {
    T save(T entity);

    Optional<T> findById(ID id);
    List<T> findAll();

    boolean deleteById(ID id);
}
