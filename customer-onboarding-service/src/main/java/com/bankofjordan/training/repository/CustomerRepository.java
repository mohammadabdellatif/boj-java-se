package com.bankofjordan.training.repository;

public interface CustomerRepository {

    boolean isRegistered(String customerId, String customerIdType);

    void save(Customer customer);
}
