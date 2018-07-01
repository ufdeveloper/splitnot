
package com.megshan.splitnot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ItemDaoException extends RuntimeException {
    public ItemDaoException(String message ) {
        super( message );
    }

    public ItemDaoException(String message, Throwable cause ) {
        super( message, cause );
    }
}
