package com.bankofjordan.services.repository;

import com.bankofjordan.services.entities.CustomerEntity;
import com.bankofjordan.services.entities.CustomerEntityRepository;

public class JPACustomerRepository implements CustomerRepository {

    private CustomerEntityRepository customerRepository;

    public JPACustomerRepository(CustomerEntityRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean isRegistered(String customerId, String customerIdType) {
        return customerRepository.existsByCustomerIdAndCustomerIdType(customerId, customerIdType);
    }

    @Override
    public void save(Customer customer) {
        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerId(customer.getCustomerId());
        entity.setCustomerIdType(customer.getCustomerIdType());
        entity.setCustomerName(customer.getCustomerName());
        entity.setCif(customer.getCif());
        entity.setNationality(customer.getNationality());
        entity.setStreet(customer.getAddress().getStreet());
        entity.setCity(customer.getAddress().getCity());
        entity.setCountry(customer.getAddress().getCountry());
        entity.setPostalCode(customer.getAddress().getPostalCode());
        entity.setRegion(customer.getAddress().getRegion());
        entity.setGender("male".equalsIgnoreCase(customer.getGender()) ? CustomerEntity.Gender.MALE : CustomerEntity.Gender.FEMALE);
        entity.setIncomeType(customer.getWealthSource().getIncomeType());
        entity.setAmount(customer.getWealthSource().getAmount());
        entity.setCurrency(customer.getWealthSource().getCurrency().getCurrencyCode());

        entity.setDateOfBirth(customer.getBirth().getDateOfBirth());
        entity.setCountryOfBirth(customer.getBirth().getCountry());

        customerRepository.save(entity);
    }
}
