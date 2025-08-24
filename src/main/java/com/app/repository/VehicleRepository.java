package com.app.repository;

import com.app.model.base.Vehicle;

import java.util.List;

public interface VehicleRepository<ID, T extends Vehicle<T>> extends CrudRepository<ID, T> {
    public List<T> findByBrand(String brand);
}
