package com.bankofjordan.training.integrator;

// Annotation: metadata to mark an interface as a single abstract function
@FunctionalInterface
public interface CivilDepIntegrator {

    public static final CivilDepIntegrator ALWAYS_VALID = (cid, cidtype) -> { };

    void validateCitizen(String customerId, String customerIdType);
}
