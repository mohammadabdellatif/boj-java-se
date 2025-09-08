package com.bankofjordan.training.integrator;

public class AlwaysValidCivilDepIntegrator implements CivilDepIntegrator {

    @Override
    public void validateCitizen(String customerId, String customerIdType) {
        // always valid
    }
}
