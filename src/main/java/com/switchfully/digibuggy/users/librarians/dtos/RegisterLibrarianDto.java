package com.switchfully.digibuggy.users.librarians.dtos;

public class RegisterLibrarianDto {
    private final String lastName;
    private final String firstName;
    private final String emailAddress;

    public RegisterLibrarianDto(String firstName, String lastName, String emailAddress) {
        this.firstName = firstName;
        this.emailAddress = emailAddress;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
