package com.switchfully.digibuggy.members;

public class MemberDto {
    private String id;
    private String inss;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String streetName;
    private String streetNumber;
    private String postalCode;
    private String cityName;

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

    public MemberDto setId(String id) {
        this.id = id;
        return this;
    }

    public MemberDto setInss(String inss) {
        this.inss = inss;
        return this;
    }

    public MemberDto setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public MemberDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public MemberDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public MemberDto setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public MemberDto setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public MemberDto setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public MemberDto setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }


}
