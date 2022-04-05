package com.switchfully.digibuggy.users.admins;

import com.switchfully.digibuggy.security.UsernamePassword;

import java.util.UUID;

public class Admin {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final UsernamePassword usernamePassword;

    public Admin(String firstName, String lastName, String emailAddress, UsernamePassword usernamePassword) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.usernamePassword = usernamePassword;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getUsername() {
        return usernamePassword.getUsername();
    }

    public String getPassword() {
        return usernamePassword.getPassword();
    }
}
