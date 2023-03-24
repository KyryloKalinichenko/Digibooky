package com.firefox5.digibooky.security;

import com.firefox5.digibooky.domain.user.UserRepository;
import com.firefox5.digibooky.service.security.Feature;
import com.firefox5.digibooky.service.security.SecurityService;
import com.firefox5.digibooky.service.security.exceptions.UnauthorizedException;
import com.firefox5.digibooky.service.security.exceptions.UserNotFoundException;
import com.firefox5.digibooky.service.security.exceptions.WrongPasswordException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@Component
class SecurityServiceTest {
    @Autowired
    private SecurityService securityService = new SecurityService(new UserRepository());

    @Test
    void whenAUserIsNotFound_AnExceptionIsThrown() {
        //given
        String auth = "Basic dGVzdEB0ZXN0LmNvbToxMjM=";

        //when
        UserNotFoundException exception = Assertions.assertThrows(UserNotFoundException.class,()->{
            securityService.validateAuthorization(auth, Feature.DELETE_A_BOOK);
        });
        //then
        Assertions.assertEquals("Unknown user email. Please try again",exception.getMessage());
    }

    @Test
    void givenAUser_whenPasswordDoesntMatch_thenAnExceptionIsThrown() {
        //given
        String auth = "Basic bWVtYmVyQGdtYWlsLmNvbToxMjQ=";
        //when
        WrongPasswordException exception = Assertions.assertThrows(WrongPasswordException.class,()->{
            securityService.validateAuthorization(auth, Feature.GET_ALL_BOOKS);
        });
        //then
        Assertions.assertEquals("Wrong password. Please try again.",exception.getMessage());
    }

    @Test
    void givenAnAuthorizedUser_whenTheUserHasNoAccessToAFeature_thenAnExceptionIsThrown() {
        //given
        String auth = "Basic bWVtYmVyQGdtYWlsLmNvbToxMjM=";
        //when
        UnauthorizedException exception = Assertions.assertThrows(UnauthorizedException.class,()->{
            securityService.validateAuthorization(auth, Feature.REGISTER_A_NEW_BOOK);
        });
        //then
        Assertions.assertEquals("Permisson denied. Please contact your administrator.",exception.getMessage());
    }
    @Test
    void givenAUserWithValidCredentials_whenTheUserHasAccessToAFeature_thenNoExceptionsThrown() {
        //given
        String auth = "Basic YWRtaW5AZ21haWwuY29tOjEyMw==";
        //when
        securityService.validateAuthorization(auth,Feature.GET_ALL_USERS);
        //then
        Assertions.assertDoesNotThrow(() ->securityService.validateAuthorization(auth,Feature.GET_ALL_USERS));
    }
}