package com.app.service.impl;

import com.app.model.base.Vehicle;
import com.app.repository.AllVehiclesRepository;
import com.app.repository.impl.AllVehiclesRepositoryImpl;
import com.app.service.AllVehiclesService;
import com.app.service.base.TransactionalService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Optional;

public class AllVehiclesServiceImpl extends TransactionalService implements AllVehiclesService<Long> {
    public AllVehiclesServiceImpl(EntityManagerFactory emf) {
        super(emf);
    }

    private AllVehiclesRepository<Long> repo(EntityManager em) {
        return new AllVehiclesRepositoryImpl(em);
    }

    @Override
    public List<Vehicle<?>> findAll() {
        return executeTransaction(em -> repo(em).findAll());
    }

    @Override
    public List<Vehicle<?>> findByBrand(String brand) {
        return executeTransaction(em -> repo(em).findByBrand(brand.toLowerCase()));
    }

    @Override
    public Optional<Vehicle<?>> findById(Long id) {
        return executeTransaction(em -> repo(em).findById(id));
    }
}
