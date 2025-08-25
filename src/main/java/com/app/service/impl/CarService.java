package com.app.service.impl;

import com.app.model.entity.Car;
import com.app.repository.CarRepository;
import com.app.repository.VehicleRepository;
import com.app.repository.impl.CarRepositoryImpl;
import com.app.repository.impl.VehicleRepositoryImpl;
import com.app.utils.ValidationUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class CarService extends VehicleServiceImpl<Long, Car> {
    public CarService(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    protected CarRepository repo(EntityManager em) {
        return new CarRepositoryImpl(em);
    }

    @Override
    protected void validateSpecificVehicleFields(Car v) {
        validateSeatingCapacity(v.getSeatingCapacity());
        ValidationUtils.validateString(v.getFuelType(), "fuelType");
    }

    public void validateSeatingCapacity(int seatingCapacity) {
        if (seatingCapacity < 1 || seatingCapacity > 20) throw new IllegalArgumentException("seatingCapacity must be between 1 and 20");
    }
}
