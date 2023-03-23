package com.firefox5.digibooky.domain.user.roles;

import com.firefox5.digibooky.api.user.MemberPostDTO;
import com.firefox5.digibooky.api.user.UserDTO;
import com.firefox5.digibooky.api.user.AdminPostDTO;
import com.firefox5.digibooky.domain.user.Address;
import com.firefox5.digibooky.domain.user.Role;
import com.firefox5.digibooky.domain.user.User;

public class Member extends User {
    public Member(String inss, String firstName, String lastName, String emailAddress, Address address, String password) {
        super(inss, firstName, lastName, emailAddress, address, password, Role.MEMBER);
    }

    @Override
    public UserDTO toDTO() {
        return new UserDTO(this);
    }

    public Member(MemberPostDTO memberPostDTO) {
        super(memberPostDTO);
    }

}
