package com.kamilcodemate.todoserver.service;

import com.kamilcodemate.todoserver.exception.InvalidTokenException;
import com.kamilcodemate.todoserver.helpers.CheckJwtToken;
import com.kamilcodemate.todoserver.repository.SubtaskRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubtaskServiceImpl implements SubtaskService{

    @Autowired
    CheckJwtToken checkJwtToken;

    @Autowired
    SubtaskRepository subtaskRepository;
    @Override
    public Integer updateSubtaskCompletion(String username, String token, Long subtaskId, boolean isDone) throws InvalidTokenException {
        String clearedToken = token.replace("Bearer", "");


        Claims tokenClaims = checkJwtToken.checkJwt(clearedToken, username);
        final String ROLE = tokenClaims.get("role").toString();


        if (ROLE.equals("USER")) {

            return subtaskRepository.updateSubtaskCompletion(isDone, subtaskId, username);
        }
        throw new InvalidTokenException("Invalid permission role");
    }
}
