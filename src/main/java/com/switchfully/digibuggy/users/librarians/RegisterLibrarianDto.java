package com.switchfully.digibuggy.users.librarians;

public class RegisterLibrarianDto {
    private final String lastname;
    private final String firstName;
    private final String emailAddress;

    public RegisterLibrarianDto(String firstName, String lastname, String emailAddress) {
        this.firstName = firstName;
        this.emailAddress = emailAddress;
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
