package com.bankofjordan.services.repository;

import com.bankofjordan.services.open.Birth;
import com.bankofjordan.services.open.ResidenceAddress;
import com.bankofjordan.services.open.WealthSource;

public class Customer {

    private String customerId;
    private String customerIdType;
    private String customerName;

    private String nationality;
    private String gender;

    private String cif;

    private Birth birth;
    private ResidenceAddress address;
    private WealthSource wealthSource;

    public Customer(String customerId,
                    String customerIdType,
                    String customerName,
                    String nationality,
                    String gender,
                    String cif,
                    Birth birth,
                    ResidenceAddress address,
                    WealthSource wealthSource) {
        this.customerId = customerId;
        this.customerIdType = customerIdType;
        this.customerName = customerName;
        this.nationality = nationality;
        this.gender = gender;
        this.cif = cif;
        this.birth = birth;
        this.address = address;
        this.wealthSource = wealthSource;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerIdType() {
        return customerIdType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getNationality() {
        return nationality;
    }

    public String getGender() {
        return gender;
    }

    public String getCif() {
        return cif;
    }

    public Birth getBirth() {
        return birth;
    }

    public ResidenceAddress getAddress() {
        return address;
    }

    public WealthSource getWealthSource() {
        return wealthSource;
    }
}
