package com.bankofjordan.services.repository;

import java.util.List;

// Interface (more than one abstract method)
public interface CustomerRepository {

    boolean isRegistered(String customerId, String customerIdType);

    void save(Customer customer);
}
