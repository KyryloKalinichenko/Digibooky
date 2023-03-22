package com.firefox5.digibooky.service.user;

import com.firefox5.digibooky.api.user.UserDTO;
import com.firefox5.digibooky.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getALlUsers(){
        return UserMapper.listOfUsersToListOfUserDTO(userRepository.getAllUsers());
    }
}
