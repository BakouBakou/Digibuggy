package com.switchfully.digibuggy.users.librarians;

import java.util.UUID;

public class Librarian {

    private final String id;
    private final String lastName;
    private final String firstName;
    private final String emailAddress;

    public Librarian(String firstName, String lastName, String emailAddress) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.emailAddress = emailAddress;
        this.lastName = lastName;
    }

    public String getID() {
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
