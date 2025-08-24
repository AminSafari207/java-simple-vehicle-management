package com.app.repository.Impl;

import com.app.model.entity.Truck;
import com.app.repository.TruckRepository;
import jakarta.persistence.EntityManager;

public class TruckRepositoryImpl extends VehicleRepositoryImpl<Long, Truck> implements TruckRepository {
    public TruckRepositoryImpl(EntityManager em) {
        super(em, Truck.class);
    }
}
