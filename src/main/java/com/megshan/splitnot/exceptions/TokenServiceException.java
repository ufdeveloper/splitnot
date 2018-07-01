
package com.megshan.splitnot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TokenServiceException extends RuntimeException {
    public TokenServiceException(String message ) {
        super( message );
    }

    public TokenServiceException(String message, Throwable cause ) {
        super( message, cause );
    }
}
