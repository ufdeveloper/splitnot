package com.megshan.splitnot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthorizationException extends HttpClientErrorException {

    public AuthorizationException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
