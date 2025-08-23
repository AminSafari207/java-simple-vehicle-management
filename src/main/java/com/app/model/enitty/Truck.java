package com.app.model.enitty;

import com.app.model.base.Vehicle;
import jakarta.persistence.Entity;

@Entity
public class Truck extends Vehicle<Long, Truck> {
    private double loadCapacity;
    private int numberOfAxles;

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public int getNumberOfAxles() {
        return numberOfAxles;
    }

    public Truck loadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
        return this;
    }

    public Truck numberOfAxles(int numberOfAxles) {
        this.numberOfAxles = numberOfAxles;
        return this;
    }
}
