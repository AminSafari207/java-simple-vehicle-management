package com.app.repository.Impl;

import com.app.model.base.Vehicle;
import com.app.repository.VehicleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public abstract class VehicleRepositoryImpl<ID, T extends Vehicle<T>> implements VehicleRepository<ID, T> {
    private final EntityManager em;
    private final Class<T> classRef;

    public VehicleRepositoryImpl(EntityManager em, Class<T> classRef) {
        this.em = em;
        this.classRef = classRef;
    }

    @Override
    public T save(T vehicle) {
        if (vehicle.getId() == null) {
            em.persist(vehicle);
            return vehicle;
        }

        return em.merge(vehicle);
    }

    @Override
    public List<T> findAll() {
        String jpql = "select v from Vehicle v";

        return em.createQuery(jpql, classRef).getResultList();
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(em.find(classRef, id));
    }

    @Override
    public List<T> findByBrand(String brand) {
        String jpql = "select v from " + classRef.getSimpleName() + " v where v.brand = :brand";

        return em.createQuery(jpql, classRef)
                .setParameter("brand", brand)
                .getResultList();
    }

    @Override
    public boolean deleteById(ID id) throws Exception {
        try {
            T ref = em.getReference(classRef, id);
            em.remove(ref);
            return true;
        } catch (EntityNotFoundException e) {
            return false;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
