package com.firefox5.digibooky.security;

import com.firefox5.digibooky.domain.user.UserRepository;
import com.firefox5.digibooky.service.security.Feature;
import com.firefox5.digibooky.service.security.SecurityService;
import com.firefox5.digibooky.service.security.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        securityService.validateAuthorization(auth, Feature.DELETE_A_BOOK);
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