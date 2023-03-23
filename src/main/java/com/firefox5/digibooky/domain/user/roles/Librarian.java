package com.firefox5.digibooky.domain.user.roles;

import com.firefox5.digibooky.api.user.UserPostDTO;
import com.firefox5.digibooky.domain.user.Address;
import com.firefox5.digibooky.domain.user.Role;
import com.firefox5.digibooky.domain.user.User;

public class Librarian extends User {
    public Librarian(String inss, String firstName, String lastName, String emailAddress, Address address, String password) {
        super(inss, firstName, lastName, emailAddress, address, password, Role.LIBRARIAN);
    }

    public Librarian(UserPostDTO userPostDTO) {
        super(userPostDTO);
    }
}
