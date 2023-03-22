package com.firefox5.digibooky.domain.user;

import com.firefox5.digibooky.api.user.UserDTO;
import com.firefox5.digibooky.api.user.UserPostDTO;

import java.util.Objects;

public class User {
    private final String inss;
    private static int counter;
    private final int userId;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final Address address;


    public User(String inss, String firstName, String lastName, String emailAddress, Address address) {
        this.inss = inss;
        this.userId = counter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
    }
    public User(UserPostDTO userPostDTO){
        inss = userPostDTO.getInss();
        userId = counter++;
        firstName = userPostDTO.getFirstName();
        lastName = userPostDTO.getLastName();
        emailAddress = userPostDTO.getEmailAddress();
        address = userPostDTO.getAddress();
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
    public final String getInss(){
        return inss;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(inss, user.inss) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inss, firstName, lastName);
    }
}
