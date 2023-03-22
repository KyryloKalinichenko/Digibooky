package com.firefox5.digibooky.security;

import com.firefox5.digibooky.domain.user.Address;
import com.firefox5.digibooky.domain.user.Role;
import com.firefox5.digibooky.domain.user.User;
import com.firefox5.digibooky.domain.user.UserRepository;
import com.firefox5.digibooky.security.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Component
class SecurityServiceTest {
    @Autowired
    private SecurityService securityService = new SecurityService(new UserRepository());

    @Test
    void whenAUserIsNotFound_AnExceptionIsThrown() {
        //given
        String auth = "dGVzdEB0ZXN0LmNvbToxMjM=";

        //when
        UserNotFoundException exception = Assertions.assertThrows(UserNotFoundException.class,()->{
        securityService.validateAuthorization(auth,Feature.DELETE_A_BOOK);
        });
        //then
        Assertions.assertEquals("",exception.getMessage());
    }

    @Test
    void givenAUser_whenPasswordDoesntMatch_thenAnExceptionIsThrown() {
        //given

        //when

        //then
    }

    @Test
    void givenAnAuthorizedUser_whenTheUserHasNoAccessToAFeature_thenAnExceptionIsThrown() {
        //given

        //when

        //then
    }
}