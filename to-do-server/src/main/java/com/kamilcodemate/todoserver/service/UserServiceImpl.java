package com.kamilcodemate.todoserver.service;


import com.kamilcodemate.todoserver.entity.User;
import com.kamilcodemate.todoserver.exception.InvalidPasswordConfirmationException;
import com.kamilcodemate.todoserver.model.ResponseWithTokenModel;
import com.kamilcodemate.todoserver.model.UserModel;
import com.kamilcodemate.todoserver.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * User Service class (implements {@link UserService}
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService{


    /**
     * PasswordEncoder Bean
     */
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * User Repository Bean
     */
    @Autowired
    UserRepository userRepository;


    /**
     * TokenServiceMethods Bean
     */
    private final TokenServiceMethods tokenServiceMethods;

    /** IOC constructor
     * @param tokenServiceMethods Bean to initialize
     */
    public UserServiceImpl(TokenServiceMethods tokenServiceMethods) {
        this.tokenServiceMethods = tokenServiceMethods;
    }

    /**
     * @param userModel User Model to register User
     * @return {@link org.springframework.http.ResponseEntity} generic parameter value (registered user)
     * @throws InvalidPasswordConfirmationException Throws when passwords are not the same
     */
    public ResponseWithTokenModel registerUser(UserModel userModel) throws InvalidPasswordConfirmationException {

        if(!(userModel.getPassword().equals(userModel.getConfirmPassword()))) throw new InvalidPasswordConfirmationException("Passwords are different");



        User user = new User();
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setUsername(userModel.getUsername());
        user.setRole(userModel.getRole());
        String token;

             token = tokenServiceMethods.generateToken(user);

            userRepository.save(user);


        return new ResponseWithTokenModel("User registered", token);

    }

    /** Fining User by username
     * @param username Username of the user to find
     * @return Found User
     */
    @Override
    public User findUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }
}
