package com.kamilcodemate.todoserver.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Passwords are diffrent.")
public class InvalidPasswordConfirmationExcpetion extends Exception {

    public InvalidPasswordConfirmationExcpetion(String message) {
        super(message);
    }
}
