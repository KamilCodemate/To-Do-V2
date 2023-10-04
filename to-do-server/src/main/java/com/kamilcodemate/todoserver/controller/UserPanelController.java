package com.kamilcodemate.todoserver.controller;

import com.kamilcodemate.todoserver.helpers.CheckJwtToken;
import com.kamilcodemate.todoserver.model.UserPanelControllerAPIModel;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.Role;

@RestController
public class UserPanelController {


    @Autowired
    CheckJwtToken checkJwtToken;





    @PostMapping("/user-panel")
    public ResponseEntity<?>  userPanel(@RequestBody UserPanelControllerAPIModel apiData)
    {

       Claims tokenClaims = checkJwtToken.checkJwt(apiData.getUser(), apiData.getToken());
        String role = tokenClaims.get("role").toString();

        if(role.equals("USER"))
        {
            return new ResponseEntity<>(role, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
