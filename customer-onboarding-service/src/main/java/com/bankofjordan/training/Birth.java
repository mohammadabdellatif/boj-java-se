package com.bankofjordan.training;

import java.time.LocalDate;

public class Birth {
    private final LocalDate dateOfBirth;
    private final String country;

    public Birth(LocalDate dateOfBirth, String country) {
        if (!isValidBirthDate(dateOfBirth))
            throw new IllegalArgumentException("Invalid date of birth");
        if (country == null)
            throw new IllegalArgumentException("Invalid country");
        this.dateOfBirth = dateOfBirth;
        this.country = country;
    }

    private boolean isValidBirthDate(LocalDate dateOfBirth) {
        return dateOfBirth != null && LocalDate.now().isAfter(dateOfBirth);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCountry() {
        return country;
    }
}
