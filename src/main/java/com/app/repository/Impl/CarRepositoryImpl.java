package com.app.repository.Impl;

import com.app.model.enitty.Car;
import com.app.repository.CarRepository;
import jakarta.persistence.EntityManager;

public class CarRepositoryImpl extends VehicleRepositoryImpl<Long, Car> implements CarRepository {
    public CarRepositoryImpl(EntityManager em) {
        super(em, Car.class);
    }
}
