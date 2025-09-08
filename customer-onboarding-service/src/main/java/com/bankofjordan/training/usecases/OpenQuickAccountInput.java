package com.bankofjordan.training.usecases;

import java.util.Arrays;
import java.util.List;

public class OpenQuickAccountInput {
    private final static List<String> GENDER_OPTIONS = Arrays.asList("male", "female");

    private String customerId;
    private String customerIdType;

    private String name;
    private String gender;

    private String nationality;
    private Birth birth;

    private ResidenceAddress residenceAddress;

    private ContactInfo contactInfo;

    private WealthSource wealthSource;

    public OpenQuickAccountInput(String customerId,
                                 String customerIdType,
                                 String name,
                                 String gender,
                                 String nationality,
                                 Birth birth,
                                 ResidenceAddress residenceAddress,
                                 ContactInfo contactInfo,
                                 WealthSource wealthSource) {
        if(customerId == null)
            throw new IllegalArgumentException("Invalid customer id");
        if(customerIdType == null)
            throw new IllegalArgumentException("Invalid customer id type");
        if(name == null)
            throw new IllegalArgumentException("Invalid name");
        if(name.split(" ").length != 4)
            throw new IllegalArgumentException("Invalid name, should have four syllabus");
        if(!GENDER_OPTIONS.contains(gender))// TODO lower case
            throw new IllegalArgumentException("Invalid gender");
        if(nationality == null)
            throw new IllegalArgumentException("Invalid nationality");
        if(birth == null)
            throw new IllegalArgumentException("Invalid birth");
        if(residenceAddress == null)
            throw new IllegalArgumentException("Invalid residence address");
        if(contactInfo == null)
            throw new IllegalArgumentException("Invalid contact info");
        if(wealthSource == null)
            throw new IllegalArgumentException("Invalid wealth source");
        this.customerId = customerId;
        this.customerIdType = customerIdType;
        this.name = name;
        this.gender = gender;
        this.nationality = nationality;
        this.birth = birth;
        this.residenceAddress = residenceAddress;
        this.contactInfo = contactInfo;
        this.wealthSource = wealthSource;
    }

    public String getCustomerId() {
        return customerId;
    }
    public String getCustomerIdType() {
        return customerIdType;
    }
    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }
    public String getNationality() {
        return nationality;
    }
    public Birth getBirth() {
        return birth;
    }
    public ResidenceAddress getResidenceAddress() {
        return residenceAddress;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }
    public WealthSource getWealthSource() {
        return wealthSource;
    }
}
