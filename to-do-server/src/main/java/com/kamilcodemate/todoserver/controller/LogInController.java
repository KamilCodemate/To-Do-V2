package com.kamilcodemate.todoserver.controller;

import com.kamilcodemate.todoserver.entity.User;
import com.kamilcodemate.todoserver.model.ResponseWithTokenLogInModel;
import com.kamilcodemate.todoserver.model.TaskModels.LogInRequestModel;
import com.kamilcodemate.todoserver.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LogInController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/api/userpanel/login")
    public ResponseEntity<?> LogInUser(@RequestBody LogInRequestModel requestData)
    {

        ResponseWithTokenLogInModel retData = userService.loginUser(requestData.getUsername(), requestData.getPassword());
        if(retData.getUser() != null && retData != null) return new ResponseEntity<>(retData, HttpStatus.OK);
        else return new ResponseEntity<>("Something went wrong. Please try again later", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
