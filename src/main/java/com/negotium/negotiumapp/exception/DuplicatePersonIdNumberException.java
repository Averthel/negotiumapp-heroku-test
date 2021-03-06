package com.negotium.negotiumapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "User with this person id number is already exist")
public class DuplicatePersonIdNumberException extends RuntimeException {
    public DuplicatePersonIdNumberException(String message) {
        super(message);
    }
}