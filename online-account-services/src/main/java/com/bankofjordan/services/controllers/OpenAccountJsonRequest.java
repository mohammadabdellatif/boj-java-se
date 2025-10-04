package com.bankofjordan.services.controllers;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class OpenAccountJsonRequest {
    public String customerId;
    public String customerIdType;
    public String name;
    public String gender;
    public String nationality;
    public LocalDate birth;
    public String birthPlace;
    public ResidenceAddress residenceAddress;
    public ContactInfo contactInfo;
    public WealthSource wealthSource;

    @Getter
    @Setter
    public class ResidenceAddress {
        public String street;
        public String region;
        public String city;
        public String country;
        public String postalCode;
    }

    @Getter
    @Setter
    public class WealthSource {
        public BigDecimal amount;
        public String currency;
        public String incomeType;
    }

    @Getter
    @Setter
    public class ContactInfo {
        public String mobileNo;
        public String email;
    }

}



