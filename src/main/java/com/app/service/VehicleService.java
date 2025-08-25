package com.app.service;

import com.app.model.base.Vehicle;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface VehicleService<ID, T extends Vehicle<T>> {
    T create(T vehicle);

    List<T> findAll();
    List<T> findByBrand(String brand);
    Optional<T> findById(ID id);

    T update(ID id, Map<String, Object> updateMap);

    boolean removeById(ID id);
}
