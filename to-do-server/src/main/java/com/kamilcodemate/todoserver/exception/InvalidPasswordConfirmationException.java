package com.kamilcodemate.todoserver.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Passwords are different.")
public class InvalidPasswordConfirmationException extends Exception {


    public InvalidPasswordConfirmationException(String message) {
        super(message);
    }
}
