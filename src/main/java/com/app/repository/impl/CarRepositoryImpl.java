package com.app.repository.impl;

import com.app.model.entity.Car;
import com.app.repository.CarRepository;
import jakarta.persistence.EntityManager;

public class CarRepositoryImpl extends VehicleRepositoryImpl<Long, Car> implements CarRepository {
    public CarRepositoryImpl(EntityManager em) {
        super(em, Car.class);
    }
}
