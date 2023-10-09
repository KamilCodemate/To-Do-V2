package com.kamilcodemate.todoserver.controller;

import com.kamilcodemate.todoserver.helpers.CheckJwtToken;
import com.kamilcodemate.todoserver.model.AuthorizationRequestModel;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserPanelController {


    @Autowired
    CheckJwtToken checkJwtToken;





    @PostMapping("/api/user-panel")
    public ResponseEntity<?>  userPanel(@RequestBody AuthorizationRequestModel apiData)
    {

       Claims tokenClaims = checkJwtToken.checkJwt(apiData.getToken());
        String role = tokenClaims.get("role").toString();

        if(role.equals("USER"))
        {
            return new ResponseEntity<>(role, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
