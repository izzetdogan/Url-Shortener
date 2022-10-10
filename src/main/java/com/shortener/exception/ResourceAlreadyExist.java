package com.shortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExist extends  RuntimeException {

    public ResourceAlreadyExist(String message) {
        super(message);
    }
}
