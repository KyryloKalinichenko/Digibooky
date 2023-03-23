package com.firefox5.digibooky.service.book;

import com.firefox5.digibooky.service.security.exceptions.UnauthorizedException;
import com.sun.net.httpserver.HttpsServer;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class BookExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected void emptyFieldsException(IllegalArgumentException exception, HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(IllegalAccessException.class)
    protected void unauthorizedException(UnauthorizedException exception, HttpServletResponse response) throws  IOException {
        response.sendError(FORBIDDEN.value(), exception.getMessage());
    }
}
