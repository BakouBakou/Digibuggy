package com.switchfully.digibuggy.users.librarians;

public class LibrarianDto {

    private final String id;
    private final String lastName;
    private final String firstName;
    private final String emailAddress;

    public LibrarianDto(String id, String firstName,String lastName, String emailAddress) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.emailAddress = emailAddress;
    }

    public String getId() {
        return id;
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
