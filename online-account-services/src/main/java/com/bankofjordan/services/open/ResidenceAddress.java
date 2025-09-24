package com.bankofjordan.services.open;

public class ResidenceAddress {
    private String street;
    private String region;
    private String city;
    private String country;
    private String postalCode;

    public ResidenceAddress(String street,
                            String region,
                            String city,
                            String country,
                            String postalCode) {
        if(street == null)
            throw new IllegalArgumentException("Invalid street");
        if(region == null)
            throw new IllegalArgumentException("Invalid region");
        if(city == null)
            throw new IllegalArgumentException("Invalid city");
        if(country == null)
            throw new IllegalArgumentException("Invalid county");
        if(postalCode == null)
            throw new IllegalArgumentException("Invalid postal code");
        this.street = street;
        this.region = region;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResidenceAddress{");
        sb.append("street='").append(street).append('\'');
        sb.append(", region='").append(region).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", county='").append(country).append('\'');
        sb.append(", postalCode='").append(postalCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
