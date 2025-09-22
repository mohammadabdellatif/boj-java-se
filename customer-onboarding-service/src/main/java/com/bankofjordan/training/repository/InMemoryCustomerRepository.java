package com.bankofjordan.training.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryCustomerRepository implements CustomerRepository {

    private final Map<String, Customer> customers = new HashMap<>();

    @Override
    public boolean isRegistered(String customerId, String customerIdType) {
        return customers.containsKey(customerId + "-" + customerIdType);
    }

    @Override
    public void save(Customer customer) {
        String customerKey = customer.getCustomerId() + "-" + customer.getCustomerIdType();
        customers.put(customerKey, customer);
    }

    @Override
    public void save(List<Customer> customers) {
        customers.forEach(this::save);
    }
}
