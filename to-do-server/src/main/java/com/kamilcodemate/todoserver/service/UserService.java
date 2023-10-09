package com.kamilcodemate.todoserver.service;

import com.kamilcodemate.todoserver.entity.User;
import com.kamilcodemate.todoserver.exception.InvalidPasswordConfirmationExcpetion;
import com.kamilcodemate.todoserver.model.ResponseWithTokenModel;
import com.kamilcodemate.todoserver.model.UserModel;

public interface UserService {
    public ResponseWithTokenModel registerUser(UserModel userModel) throws InvalidPasswordConfirmationExcpetion;

    public User findUserByUsername(String username);
}
