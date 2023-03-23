package com.firefox5.digibooky.domain.user;

import com.firefox5.digibooky.api.user.AdminPostDTO;
import com.firefox5.digibooky.api.user.MemberPostDTO;
import com.firefox5.digibooky.api.user.UserDTO;
import com.firefox5.digibooky.service.security.Feature;

import java.util.Objects;

public abstract class User {
    private final String inss;
    private static int counter;
    private final int userId;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final Address address;
    private final String password;
    private Role role;



    public User(String inss, String firstName, String lastName, String emailAddress, Address address, String password, Role role) {

        this.inss = inss;
        this.userId = counter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.password = password;
        this.role = role;
    }

    public User(AdminPostDTO adminPostDTO) {
        inss = adminPostDTO.getInss();
        userId = counter++;
        firstName = adminPostDTO.getFirstName();
        lastName = adminPostDTO.getLastName();
        emailAddress = adminPostDTO.getEmailAddress();
        address = adminPostDTO.getAddress();
        password = adminPostDTO.getPassword();
    }

    public User(MemberPostDTO memberPostDTO) {
        inss = memberPostDTO.getInss();
        userId = counter++;
        firstName = memberPostDTO.getFirstName();
        lastName = memberPostDTO.getLastName();
        emailAddress = memberPostDTO.getEmailAddress();
        address = memberPostDTO.getAddress();
        password = memberPostDTO.getPassword();
    }

    public abstract UserDTO toDTO();

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

    public final String getInss() {
        return inss;
    }


    public boolean canHaveAccessTo(Feature feature) {
        return role.containsFeature(feature);
    }

    public boolean doesPasswordMatch(String passwordToBeChecked) {
        return this.password.equals(passwordToBeChecked);
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(inss, user.inss) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName);
    }

    public Role getRole() {
        return role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inss, firstName, lastName);

    }
}
