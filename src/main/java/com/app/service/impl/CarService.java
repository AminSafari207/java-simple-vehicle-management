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

    public void testDetachThenChange() {
        Long carId = 3L;
        String newFuelType = "TEST_FUEL_TYPE";

        executeTransactionVoid(em -> {
            Car managedCarBecauseRead = em.find(Car.class, carId);

            String fuelTypeBefore = managedCarBecauseRead.getFuelType();

            boolean isManagedBeforeDetach = em.contains(managedCarBecauseRead);
            em.detach(managedCarBecauseRead);
            boolean isManagedAfterDetach = em.contains(managedCarBecauseRead);

            managedCarBecauseRead.fuelType(newFuelType);

            em.flush();

            String newFetchedFuelType = em.find(Car.class, carId).getFuelType();

            System.out.println("###########################");
            System.out.println();
            System.out.println("Fuel type at first: " + fuelTypeBefore);
            System.out.println("Is managed before detach: " + isManagedBeforeDetach);
            System.out.println("Detaching managed fetched Car...");
            System.out.println("Is managed after detach: " + isManagedAfterDetach);
            System.out.println("Changing entity fuelType field...");
            System.out.println("Entity manager flushed right now...");
            System.out.println("Fuel type after detached car flush: " + newFetchedFuelType);
            System.out.println();
            System.out.println("###########################");
        });
    }

    public void testDetachThenMerge() {
        Long carId = 4L;
        String newFuelType = "TEST_FUEL_TYPE";

        executeTransactionVoid(em -> {
            Car managedCarBecauseRead = em.find(Car.class, carId);

            String fuelTypeBefore = managedCarBecauseRead.getFuelType();

            boolean isManagedBeforeDetach = em.contains(managedCarBecauseRead);
            em.detach(managedCarBecauseRead);
            boolean isManagedAfterDetach = em.contains(managedCarBecauseRead);

            managedCarBecauseRead.fuelType(newFuelType);

            em.merge(managedCarBecauseRead);
            em.flush();

            System.out.println("###########################");
            System.out.println();
            System.out.println("Fuel type at first: " + fuelTypeBefore);
            System.out.println("Is managed before detach: " + isManagedBeforeDetach);
            System.out.println("Detaching managed fetched Car...");
            System.out.println("Is managed after detach: " + isManagedAfterDetach);
            System.out.println("Changing Car entity fuelType field...");
            System.out.println("Merging Car entity with new fuelType...");
            System.out.println("Entity manager flushed right now...");
        });

        String newFetchedFuelType = findById(carId).get().getFuelType();

        System.out.println("New fetched fuel type: " + newFetchedFuelType);
        System.out.println();
        System.out.println("###########################");
    }
}
