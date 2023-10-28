package com.kamilcodemate.todoserver.service;

import com.kamilcodemate.todoserver.entity.User;
import com.kamilcodemate.todoserver.exception.InvalidPasswordConfirmationException;
import com.kamilcodemate.todoserver.model.ResponseWithTokenModel;
import com.kamilcodemate.todoserver.model.UserModel;

/**
 * User Service Interface
 */
public interface UserService {
     /** Registering User
      * @param userModel User Model to register User
      * @return {@link org.springframework.http.ResponseEntity} generic parameter value
      * @throws InvalidPasswordConfirmationException Throws when passwords are not the same
      */
     ResponseWithTokenModel registerUser(UserModel userModel) throws InvalidPasswordConfirmationException;

     /** Getting User by username
      * @param username User username
      * @return Found User
      */
     User findUserByUsername(String username);
}
