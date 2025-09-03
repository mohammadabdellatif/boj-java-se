package com.bankofjordan.training;

public interface CustomerRepository {

    boolean isRegistered(String customerId, String customerIdType);
}
