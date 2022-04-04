package com.switchfully.digibuggy.members.dtos;


public class RegisterMemberDto {
    private final String inss;
    private final String emailAddress;
    private final String firstName;
    private final String lastName;
    private final String streetName;
    private final String streetNumber;
    private final String postalCode;
    private final String cityName;

    public RegisterMemberDto(String inss,
                             String emailAddress,
                             String firstName,
                             String lastName,
                             String streetName,
                             String streetNumber,
                             String postalCode,
                             String cityName) {
        this.inss = inss;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.cityName = cityName;
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
