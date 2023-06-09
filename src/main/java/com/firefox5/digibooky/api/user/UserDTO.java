package com.firefox5.digibooky.api.user;

import com.firefox5.digibooky.domain.user.Address;
import com.firefox5.digibooky.domain.user.User;

public class UserDTO {

    private final int userId;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final Address address;

    public UserDTO(int id, String firstName, String lastName, String emailAddress, Address address) {
        this.userId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
    }

    public UserDTO(User toCopy) {
        this.userId = toCopy.getUserId();
        this.firstName = toCopy.getFirstName();
        this.lastName = toCopy.getLastName();
        this.emailAddress = toCopy.getEmailAddress();
        this.address = toCopy.getAddress();
    }

    public final int getUserId() {
        return userId;
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

    public Address getAddress() {
        return address;
    }
}
