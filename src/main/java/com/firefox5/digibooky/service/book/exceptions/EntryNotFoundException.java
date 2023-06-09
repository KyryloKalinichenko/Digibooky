package com.firefox5.digibooky.service.book.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.webjars.NotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntryNotFoundException extends NotFoundException {
    public EntryNotFoundException(String message) {super(message);}
}
