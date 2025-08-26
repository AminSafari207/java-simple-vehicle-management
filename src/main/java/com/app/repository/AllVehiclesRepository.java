package com.app.repository;

import com.app.model.base.Vehicle;

import java.util.List;
import java.util.Optional;

public interface AllVehiclesRepository<ID> {
    List<Vehicle<?>> findAll();
    List<Vehicle<?>> findByBrand(String brand);
    Optional<Vehicle<?>> findById(ID id);
}
