package com.firefox5.digibooky.domain.user;

public class Address {
    private final String streetName;
    private final int streetNumber;
    private final String postalCode;
    private final String city;

    public Address(String streetName, int streetNumber, String postalCode, String city) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

