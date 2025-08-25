package com.app.model.embeddable;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public class Registration {
    private String state;
    private String plateNumber;
    private LocalDate registrationDate;

    public String getState() {
        return state;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public Registration state(String state) {
        this.state = state;
        return this;
    }

    public Registration plateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
        return this;
    }

    public Registration registrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    @Override
    public String toString() {
        return "--- Registration ---" +
                "\nState: " + state +
                "\nPlate: " + plateNumber +
                "\nRegistration Date: " + registrationDate +
                "\n-------------------";
    }
}
