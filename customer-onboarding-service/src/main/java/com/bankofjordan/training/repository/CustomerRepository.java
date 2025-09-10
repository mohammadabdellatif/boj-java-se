package com.bankofjordan.training.repository;

// Interface (more than one abstract method)
public interface CustomerRepository {

    boolean isRegistered(String customerId, String customerIdType);

    void save(Customer customer);
}
