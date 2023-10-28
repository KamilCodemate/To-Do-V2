package com.kamilcodemate.todoserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception if Bearer Token is invalid
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class InvalidTokenException extends Exception{

    /** Exception if Bearer Token is invalid
     * @param message message
     */
    public InvalidTokenException(String message)
    {
        super(message);
    }
}
