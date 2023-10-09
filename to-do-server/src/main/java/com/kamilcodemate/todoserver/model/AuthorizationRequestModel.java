package com.kamilcodemate.todoserver.model;

import com.kamilcodemate.todoserver.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorizationRequestModel {

    private User user;
    private String token;
}
