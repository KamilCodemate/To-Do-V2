package com.kamilcodemate.todoserver.controller;

import com.kamilcodemate.todoserver.exception.InvalidPasswordConfirmationExcpetion;
import com.kamilcodemate.todoserver.model.ResponseWithTokenModel;
import com.kamilcodemate.todoserver.model.UserModel;
import com.kamilcodemate.todoserver.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterUserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/api/register")
    public ResponseEntity<?> registerUser(@RequestBody UserModel userModel) throws InvalidPasswordConfirmationExcpetion {
        ResponseWithTokenModel response = userService.registerUser(userModel);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
