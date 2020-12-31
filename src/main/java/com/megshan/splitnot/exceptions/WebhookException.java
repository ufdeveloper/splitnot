package com.megshan.splitnot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class WebhookException extends HttpClientErrorException {

    public WebhookException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
