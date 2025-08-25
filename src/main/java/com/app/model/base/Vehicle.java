package com.app.model.base;

import com.app.model.enums.VehicleStatus;
import com.app.model.embeddable.Registration;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehicle<T extends Vehicle<T>> extends BaseEntity<Long> {
    private String brand;
    private String model;
    private int year;

    @Embedded
    private Registration registration;

    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public Registration getRegistration() {
        return registration;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public T brand(String brand) {
        this.brand = brand;
        return self();
    }

    public T model(String model) {
        this.model = model;
        return self();
    }

    public T year(int year) {
        this.year = year;
        return self();
    }

    public T registration(Registration registration) {
        this.registration = registration;
        return self();
    }

    public T status(VehicleStatus status) {
        this.status = status;
        return self();
    }

    private T self() {
        return (T) this;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nBrand: " + brand +
                "\nModel: " + model +
                "\nYear: " + year +
                "\nStatus: " + status +
                "\n--- Registration ---" + registration;
    }
}
