package com.app.repository;

import com.app.model.base.Vehicle;

import java.util.Optional;

public interface VehicleRepository<ID, T extends Vehicle<ID, T>> extends CrudRepository<ID, T> {
    public Optional<T> findByBrand(String brand);
}
