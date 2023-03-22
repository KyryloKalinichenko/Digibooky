package com.firefox5.digibooky.service.book;

import com.sun.net.httpserver.HttpsServer;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNSUPPORTED_MEDIA_TYPE;

@ControllerAdvice
public class BookExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected void emptyFieldsException(IllegalArgumentException exception, HttpServletResponse response) throws IOException {
        response.sendError(UNSUPPORTED_MEDIA_TYPE.value(), exception.getMessage());
    }

}
