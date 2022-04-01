package com.switchfully.digibuggy.members;


public class RegisterMemberDto {
    private String inss;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String streetName;
    private String streetNumber;
    private String postalCode;
    private String cityName;


    public RegisterMemberDto setInss(String inss) {
        this.inss = inss;
        return this;
    }

    public RegisterMemberDto setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public RegisterMemberDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public RegisterMemberDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public RegisterMemberDto setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public RegisterMemberDto setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public RegisterMemberDto setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public RegisterMemberDto setCityName(String cityName) {
        this.cityName = cityName;
        return this;
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
