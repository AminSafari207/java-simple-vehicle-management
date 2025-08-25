package com.app.service.impl;

import com.app.model.entity.Truck;
import com.app.repository.TruckRepository;
import com.app.repository.VehicleRepository;
import com.app.repository.impl.TruckRepositoryImpl;
import com.app.repository.impl.VehicleRepositoryImpl;
import com.app.utils.ValidationUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class TruckService extends VehicleServiceImpl<Long, Truck> {
    public TruckService(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    protected TruckRepository repo(EntityManager em) {
        return new TruckRepositoryImpl(em);
    }

    @Override
    protected void validateSpecificVehicleFields(Truck v) {
        if (v.getNumberOfAxles() < 2) throw new IllegalArgumentException("numberOfAxles must be higher than 1.");
        if (v.getLoadCapacity() < 5D || v.getLoadCapacity() > 100D) throw new IllegalArgumentException("loadCapacity must be between 5 and 100.");
    }

}
