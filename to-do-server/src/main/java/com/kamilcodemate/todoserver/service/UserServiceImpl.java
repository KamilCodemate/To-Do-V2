package com.kamilcodemate.todoserver.service;


import com.kamilcodemate.todoserver.entity.User;
import com.kamilcodemate.todoserver.exception.InvalidPasswordConfirmationExcpetion;
import com.kamilcodemate.todoserver.model.ResponseWithTokenModel;
import com.kamilcodemate.todoserver.model.UserModel;
import com.kamilcodemate.todoserver.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;


    private final TokenService tokenService;

    public UserServiceImpl(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public ResponseEntity<?> registerUser(UserModel userModel) throws InvalidPasswordConfirmationExcpetion {
        logger.info("Password="+ userModel.getPassword());
        logger.info("ConfirmPassword="+ userModel.getConfirmPassword());
        if(!(userModel.getPassword().equals(userModel.getConfirmPassword()))) throw new InvalidPasswordConfirmationExcpetion("Passwords are diffrent");



        User user = new User();
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setUsername(userModel.getUsername());
        user.setRole(userModel.getRole());
        String token;

             token = tokenService.generateToken(user);

            userRepository.save(user);


        ResponseWithTokenModel responseModel = new ResponseWithTokenModel("User registered", token);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);


    }
}
