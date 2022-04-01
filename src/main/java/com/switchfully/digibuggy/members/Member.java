package com.switchfully.digibuggy.members;

import java.util.UUID;

public class Member {
    private final String id;
    private final String inss;
    private final String emailAddress;
    private final String firstName;
    private final String lastName;
    private final String streetName;
    private final String streetNumber;
    private final String postalCode;
    private final String cityName;

    public Member(String inss, String emailAddress, String firstName, String lastName, String streetName, String streetNumber, String postalCode, String cityName) {
        this.id = UUID.randomUUID().toString();
        this.inss = inss;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.cityName = cityName;
    }

    public String getId() {
        return id;
    }

    public String getInss() {
        return inss;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCityName() {
        return cityName;
    }
}
