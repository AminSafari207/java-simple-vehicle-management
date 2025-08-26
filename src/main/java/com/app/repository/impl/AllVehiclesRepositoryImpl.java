package com.app.repository.impl;

import com.app.model.base.Vehicle;
import com.app.repository.AllVehiclesRepository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AllVehiclesRepositoryImpl implements AllVehiclesRepository<Long> {
    private final EntityManager em;

    public AllVehiclesRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Vehicle<?>> findAll() {
        return em.createQuery("select v from Vehicle v", Vehicle.class)
                .getResultList()
                .stream()
                .map(v -> (Vehicle<?>) v)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle<?>> findByBrand(String brand) {
        return em.createQuery("select v from Vehicle v where lower(v.brand) = :brand", Vehicle.class)
                .setParameter("brand", brand.toLowerCase())
                .getResultList()
                .stream().map(v -> (Vehicle<?>) v)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Vehicle<?>> findById(Long id) {
        return Optional.ofNullable(em.find(Vehicle.class, id)).map(v -> (Vehicle<?>) v);
    }
}
