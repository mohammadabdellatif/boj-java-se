package com.bankofjordan.services.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "CUSTOMERS")
public class CustomerEntity implements Serializable {

    @Id
    private Long id;
    private String customerId;
    private String customerIdType;
    private String customerName;
    private String nationality;
    private String gender;
    private String cif;
    private LocalDate dateOfBirth;
    private String countryOfBirth;
    private String street;
    private String region;
    private String city;
    private String country;
    private String postalCode;
    private BigDecimal amount;
    private String currency;
    private String incomeType;
}
