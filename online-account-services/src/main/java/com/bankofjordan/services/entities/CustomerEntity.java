package com.bankofjordan.services.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "CUSTOMERS")
@Getter
@Setter
@EqualsAndHashCode
public class CustomerEntity implements Serializable {

    public enum Gender {
        MALE, FEMALE
    }

    @Id
    private Long id;

    @Column(name = "cust_id", nullable = false, length = 100)
    private String customerId;

    @Column(name = "cust_id_type", nullable = false, length = 50)
    private String customerIdType;

    @Column(name = "cust_name", nullable = false, length = 500)
    private String customerName;

    @Column(name = "cust_nat", nullable = false, length = 500)
    private String nationality;

    @Column(name = "cust_gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "cust_cif")
    private String cif;

    @Column(name = "cust_birth_date")
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;

    @Column(name = "cust_birth_ctry")
    private String countryOfBirth;

    @Column(name = "cust_addr_street")
    private String street;
    @Column(name = "cust_addr_region")
    private String region;
    @Column(name = "cust_addr_city")
    private String city;
    @Column(name = "cust_addr_ctry")
    private String country;
    @Column(name = "cust_addr_pscd")
    private String postalCode;
    @Column(name = "cust_wlth_amnt")
    private BigDecimal amount;
    @Column(name = "cust_wlth_curr")
    private String currency;
    @Column(name = "cust_wlth_incm_type")
    private String incomeType;

}
