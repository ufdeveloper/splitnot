
package com.megshan.splitnot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UserDaoException extends RuntimeException {
    public UserDaoException(String message ) {
        super( message );
    }

    public UserDaoException(String message, Throwable cause ) {
        super( message, cause );
    }
}
