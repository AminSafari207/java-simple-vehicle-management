package com.app.service.impl;

import com.app.model.base.Vehicle;
import com.app.model.entity.Car;
import com.app.model.entity.Truck;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AllVehiclesServiceImpl extends AllVehiclesBaseServiceImpl {
    public AllVehiclesServiceImpl(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Car> getFilteredCars(Predicate<Car> predicate) {
        List<Vehicle<?>> allVehicles = findAll();

        return allVehicles.stream()
                .filter(v -> v instanceof Car)
                .map(v -> (Car) v)
                .filter(predicate)
                .toList();
    }

    public List<Truck> getFilteredTrucks(Predicate<Truck> predicate) {
        List<Vehicle<?>> allVehicles = findAll();

        return allVehicles.stream()
                .filter(v -> v instanceof Truck)
                .map(v -> (Truck) v)
                .filter(predicate)
                .toList();
    }

    public int calculateAvgYears() {
        List<Vehicle<?>> allVehicles = findAll();

        return (int) allVehicles.stream()
                .mapToInt(Vehicle::getYear)
                .average()
                .orElse(0);
    }

    public Map<String, Long> getVehiclesByBrandAndBrandCount() {
        List<Vehicle<?>> allVehicles = findAll();

        return allVehicles.stream()
                .collect(Collectors.groupingBy(
                        Vehicle::getBrand,
                        Collectors.counting()
                ));
    }
}
