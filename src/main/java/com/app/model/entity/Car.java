package com.app.model.entity;

import com.app.model.base.Vehicle;
import jakarta.persistence.Entity;

@Entity
public class Car extends Vehicle<Car> {
    private int seatingCapacity;
    private String fuelType;

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public Car seatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
        return this;
    }

    public Car fuelType(String fuelType) {
        this.fuelType = fuelType;
        return this;
    }
}
