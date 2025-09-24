package com.bankofjordan.services.entities;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomerEntityRepository
        extends org.springframework.data.repository.Repository<CustomerEntity, Integer> {

    boolean existsByCustomerIdAndCustomerIdType(String customerId, String customerIdType);

    CustomerEntity save(CustomerEntity customerEntity);
}
