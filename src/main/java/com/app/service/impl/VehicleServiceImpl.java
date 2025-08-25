package com.app.service.impl;

import com.app.exception.VehicleNotFoundException;
import com.app.model.base.Vehicle;
import com.app.model.embeddable.Registration;
import com.app.repository.VehicleRepository;
import com.app.service.VehicleService;
import com.app.service.base.TransactionalService;
import com.app.utils.ValidationUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class VehicleServiceImpl<ID, T extends Vehicle<T>> extends TransactionalService implements VehicleService<ID, T> {
    public VehicleServiceImpl(EntityManagerFactory emf) {
        super(emf);
    }

    ////////////////////////////////////
    /// Abstract methods ///////////////
    ////////////////////////////////////

    protected abstract VehicleRepository<ID, T> repo(EntityManager em);
    protected abstract void validateSpecificVehicleFields(T v);

    ////////////////////////////////////
    /// Main service methods ///////////
    ////////////////////////////////////

    @Override
    public T create(T vehicle) {
        validateVehicleForCreate(vehicle);

        return executeTransaction(em -> repo(em).save(vehicle));
    }

    @Override
    public List<T> findAll() {
        return executeTransaction(em -> repo(em).findAll());
    }

    @Override
    public List<T> findByBrand(String brand) {
        validateBrand(brand);

        return executeTransaction(em -> repo(em).findByBrand(brand));
    }

    @Override
    public Optional<T> findById(ID id) {
        if (id instanceof Long) ValidationUtils.validateIdLong((Long) id, "id");

        return executeTransaction(em -> repo(em).findById(id));
    }

    @Override
    public T update(ID id, Map<String, Object> updateMap) {
        if (id instanceof Long) ValidationUtils.validateIdLong((Long) id, "id");
        ValidationUtils.validateMapBasics(updateMap, "updateMap");

        return executeTransaction(em -> {
            VehicleRepository<ID, T> repo = repo(em);

            T vehicle = repo.findById(id).orElseThrow(
                    () -> new VehicleNotFoundException(repo.getClassRef().getSimpleName() + " with ID '" + id + "' not found.")
            );

            if (updateMap.containsKey("model")) {
                Object model = updateMap.get("model");

                if (!(model instanceof String)) throw new IllegalArgumentException("'updateMap.model' must be String.");
                ValidationUtils.validateString((String) model, "updateMap.model");

                vehicle.model((String) model);
            }

            if (updateMap.containsKey("year")) {
                Object year = updateMap.get("year");

                if (!(year instanceof int)) throw new IllegalArgumentException("'updateMap.year' must be int.");
                validateYear((int) year, "updateMap.year");

                vehicle.year((int) year);
            }

            return vehicle;
        });
    }

    @Override
    public boolean removeById(ID id) {
        return executeTransaction(em -> {
            try {
                return repo(em).deleteById(id);
            } catch (Exception e) {
                throw new RuntimeException("VehicleServiceImpl.removeById failed.", e);
            }
        });
    }

    ////////////////////////////////////
    /// Validators /////////////////////
    ////////////////////////////////////

    public void validateBrand(String brand) {
        ValidationUtils.validateString(brand, 3, "brand");
    }

    public void validateYear(int year, String logName) {
        if (year < 1500 || year > LocalDate.now().getYear()) throw new IllegalArgumentException(logName + " must be between 1500 and current year.");
    }

    private void validateRegistration(Registration r) {
        ValidationUtils.validateNotNull(r, "'Registration r'");
        ValidationUtils.validateString(r.getPlateNumber(), 6, "plateNumber");
        ValidationUtils.validateString(r.getState(), "state");
        ValidationUtils.validateLocalDate(r.getRegistrationDate(), "registrationDate");
    }

    private void validateBaseVehicleFields(T v) {
        validateBrand(v.getBrand());
        ValidationUtils.validateString(v.getModel(), 3, "model");
        ValidationUtils.validateNotNull(v.getStatus(), "status");
        validateYear(v.getYear(), "year");
        validateRegistration(v.getRegistration());
    }

    private void validateVehicleForCreate(T vehicle) {
        ValidationUtils.validateNotNull(vehicle, "vehicle");
        validateBaseVehicleFields(vehicle);
        validateSpecificVehicleFields(vehicle);
    }

    private void validateVehicleForUpdate(T vehicle) {
        ValidationUtils.validateIdLong(vehicle.getId(), "id");
        validateVehicleForCreate(vehicle);
    }
}
