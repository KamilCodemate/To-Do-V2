package com.kamilcodemate.todoserver.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception if passwords are not the same
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Passwords are different.")
public class InvalidPasswordConfirmationException extends Exception {

    /** Passwords are not the same exception
     * @param message Message
     */
    public InvalidPasswordConfirmationException(String message) {
        super(message);
    }
}
