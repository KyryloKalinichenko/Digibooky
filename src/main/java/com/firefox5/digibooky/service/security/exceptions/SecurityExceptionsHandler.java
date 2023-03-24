package com.firefox5.digibooky.service.security.exceptions;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class SecurityExceptionsHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UnauthorizedException.class)
    protected void unauthorizedException(UnauthorizedException exception, HttpServletResponse response) throws IOException {
        response.sendError(UNAUTHORIZED.value(), exception.getMessage());
    }

    @ExceptionHandler(WrongPasswordException.class)
    protected void wrongPasswordException(WrongPasswordException exception, HttpServletResponse response) throws  IOException {
        response.sendError(BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected void userNotFoundException(UserNotFoundException exception, HttpServletResponse response) throws  IOException {
        response.sendError(NOT_FOUND.value(), exception.getMessage());
    }
}
