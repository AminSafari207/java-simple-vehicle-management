package com.app.model;

import jakarta.persistence.*;

@Entity
@Inheritance()
public class Vehicle<ID extends Number> extends BaseEntity<ID> {
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

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
