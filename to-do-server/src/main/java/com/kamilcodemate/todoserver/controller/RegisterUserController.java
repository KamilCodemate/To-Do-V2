package com.kamilcodemate.todoserver.controller;

import com.kamilcodemate.todoserver.exception.InvalidPasswordConfirmationException;
import com.kamilcodemate.todoserver.model.ResponseWithTokenModel;
import com.kamilcodemate.todoserver.model.UserModel;
import com.kamilcodemate.todoserver.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for registering a new user.
 */
@RestController
public class RegisterUserController {

    /**
     * Service class for user.
     */
    private final UserServiceImpl userService;

    /**
     * Constructor for userService.
     * @param userService Initialization value
     */
    public RegisterUserController(final UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * Registering a new user.
     * @param userModel User model to register a new user
     * @return ResponseEntity with HttpStatus and registered user (if successful).
     * @throws InvalidPasswordConfirmationException Throws when passwords are not the same.
     */
    @PostMapping("/api/register")
    public ResponseEntity<?> registerUser(@RequestBody final UserModel userModel)
            throws InvalidPasswordConfirmationException {
        ResponseWithTokenModel response = userService.registerUser(userModel);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
