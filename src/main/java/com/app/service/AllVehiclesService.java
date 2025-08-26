package com.app.service;

import com.app.model.base.Vehicle;

import java.util.List;
import java.util.Optional;

public interface AllVehiclesService<ID> {
    List<Vehicle<?>> findAll();
    List<Vehicle<?>> findByBrand(String brand);
    Optional<Vehicle<?>> findById(ID id);
}
