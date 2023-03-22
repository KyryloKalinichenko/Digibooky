package com.firefox5.digibooky.service.user;

import com.firefox5.digibooky.api.user.UserDTO;
import com.firefox5.digibooky.api.user.UserPostDTO;
import com.firefox5.digibooky.domain.user.User;

import java.util.List;

public class UserMapper {

    public static List<UserDTO> listOfUsersToListOfUserDTO(List<User> listOfUsers){
        return listOfUsers.stream()
                .map(x -> toDTO(x))
                .toList();
    }

    public static UserDTO toDTO(User user){
        return new UserDTO(user);
    }
    public static User toUser(UserPostDTO userPostDTO){
        return new User(userPostDTO);
    }

}
