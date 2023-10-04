package com.kamilcodemate.todoserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class InvalidTokenException extends Exception{

    InvalidTokenException(String message)
    {
        super(message);
    }
}
