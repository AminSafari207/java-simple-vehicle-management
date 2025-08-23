package com.app.model;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public class Registration {
    private String state;
    private String plateNumber;
    private LocalDate registrationDate;

    public Registration(String state, String plateNumber, LocalDate registrationDate) {
        this.state = state;
        this.plateNumber = plateNumber;
        this.registrationDate = registrationDate;
    }

    public Registration() {}

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
