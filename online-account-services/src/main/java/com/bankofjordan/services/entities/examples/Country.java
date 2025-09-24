package com.bankofjordan.services.entities.examples;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "COUNTRIES")
public class Country implements Serializable {
    @Id
    @Column(name = "id",length = 4,nullable = false)
    private Short id;
    @Column(name = "cntry_name", nullable = false, length = 500)
    private String name;
    @Column(name = "cntry_symbl", nullable = false, length = 4)
    private String symbol;
    @Column(name = "cntry_iso_numr", nullable = false, length = 6)
    private Short isoNumericCode;
    @Column(name = "cntry_creation_date", nullable = false)
    private LocalDate creationDate;
    @Column(name="cntry_curr_symbl")
    private String currencySymbol;
}
