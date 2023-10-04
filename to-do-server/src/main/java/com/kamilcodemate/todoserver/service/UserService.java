package com.kamilcodemate.todoserver.service;

import com.kamilcodemate.todoserver.exception.InvalidPasswordConfirmationExcpetion;
import com.kamilcodemate.todoserver.model.UserModel;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public ResponseEntity<?> registerUser(UserModel userModel) throws InvalidPasswordConfirmationExcpetion;
}
