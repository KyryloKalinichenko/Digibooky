package com.firefox5.digibooky.service.security;

import com.firefox5.digibooky.domain.user.User;
import com.firefox5.digibooky.domain.user.UserRepository;
import com.firefox5.digibooky.service.security.exceptions.UnauthorizedException;
import com.firefox5.digibooky.service.security.exceptions.UserNotFoundException;
import com.firefox5.digibooky.service.security.exceptions.WrongPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Base64;

@Service
public class SecurityService {
    private UserRepository userRepository;

    @Autowired
    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int validateAuthorization(String authorization, Feature feature){
        Credential decodedAuthorization = decodeAuth(authorization);
        User user = userRepository.getUserByEmail(decodedAuthorization.getEmail());

        if (!user.doesPasswordMatch(decodedAuthorization.getPassword())){
            throw new WrongPasswordException("Wrong password. Please try again.");
        }
        if (!user.canHaveAccessTo(feature)){
            throw new UnauthorizedException("Permisson denied. Please contact your administrator.");
        }
        return user.getUserId();
    }

    private Credential decodeAuth(String authorization) {
        String decodedAuthorization = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String email = decodedAuthorization.substring(0,decodedAuthorization.indexOf(":"));
        String password = decodedAuthorization.substring(decodedAuthorization.indexOf(":")+1);
        return new Credential(email,password);
    }
}
