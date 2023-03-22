package com.firefox5.digibooky.api.user;

import com.firefox5.digibooky.domain.user.Address;
import com.firefox5.digibooky.domain.user.User;

public class UserPostDTO {

    private final String inss;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final Address address;
    private final String password;

    public UserPostDTO(String inss, String firstName, String lastName, String emailAddress, Address address, String password) {
        this.inss = inss;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.password = password;
    }

    public UserPostDTO(User toCopy) {
        this.inss = toCopy.getInss();
        this.firstName = toCopy.getFirstName();
        this.lastName = toCopy.getLastName();
        this.emailAddress = toCopy.getEmailAddress();
        this.address = toCopy.getAddress();
        this.password =toCopy.getPassword();
    }

    public final String getInss() {
        return inss;
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

    public String getPassword() {
        return password;
    }
}
