package com.switchfully.digibuggy.members;


public class RegisterMemberDto {
    private String INSS;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String streetName;
    private String streetNumber;
    private String postalCode;
    private String cityName;


    public RegisterMemberDto setINSS(String INSS) {
        this.INSS = INSS;
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


    public String getINSS() {
        return INSS;
    }

    public String getEmailAddress() {
    }
}
