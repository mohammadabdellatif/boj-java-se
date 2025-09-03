package com.bankofjordan.training;

public class OpenQuickAccountHandler {

    // Dependency
    private final CustomerRepository customerRepository;

    // Dependency Injection
    public OpenQuickAccountHandler(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public OpenQuickAccountOutput open(OpenQuickAccountInput input) {
        // check if the customer is not already registered
        if(customerRepository.isRegistered(input.getCustomerId(), input.getCustomerIdType()))
            throw new IllegalStateException("Customer already registered");
        // check if the provided ID is a valid ID with the government
        // screening the customer name against AML lists
        // birth date should be 18+
        // generate CIF
        // generate account from the CIF
        // generate IBAN
        return null;
    }
}

