package com.firefox5.digibooky.service.user;

import com.firefox5.digibooky.api.user.UserDTO;
import com.firefox5.digibooky.api.user.UserPostDTO;
import com.firefox5.digibooky.domain.user.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getALlUsers() {
        return UserMapper.listOfUsersToListOfUserDTO(userRepository.getAllUsers());
    }


    private boolean inputVerification(UserPostDTO userPostDTO) {
        return isInssValid(userPostDTO.getInss())
                && isEmailValid(userPostDTO.getEmailAddress())
                && isValidPostcode(userPostDTO.getAddress().getPostalCode())
                && isValidCity(userPostDTO.getAddress().getCity());

    }

    private boolean isValidCity(String city) {
        return city.matches("[A-Za-z-]+");
    }

    private boolean isValidPostcode(String postalCode) {
        return postalCode.matches("\\d{4}") && postalCode.length() == 4;
    }

    private boolean isInssValid(String inss) {
        if (inss == null || inss.length() != 11 || !inss.matches("\\d+")) {
            return false;
        }
        int month, day;

        month = Integer.parseInt(inss.substring(2, 4));
        day = Integer.parseInt(inss.substring(4, 6));

        if (month < 1 || month > 12 || day < 1 || day > 31)
            return false;
        return true;
    }

    private boolean isEmailValid(String emailAddress) {
        return emailAddress != null && emailAddress.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"); // TO VERIFY
    }
    

    public UserDTO createUser(UserPostDTO userPostDTO) {
        if (!inputVerification(userPostDTO)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Verify the input and try again!");
        }


        return UserMapper.toDTO(userRepository.addUser(UserMapper.toUser(userPostDTO)));
    }
}
