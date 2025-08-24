package com.app.repository;

import com.app.model.base.Vehicle;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<ID, T> {
    T save(T entity);

    List<T> findAll();
    Optional<T> findById(ID id);

    boolean deleteById(ID id) throws Exception;
}
