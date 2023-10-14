package com.kamilcodemate.todoserver.controller;

import com.kamilcodemate.todoserver.entity.Task;
import com.kamilcodemate.todoserver.helpers.CheckJwtToken;
import com.kamilcodemate.todoserver.model.AuthorizationRequestModel;
import com.kamilcodemate.todoserver.model.TaskModels.GetAllTasksByDateAPIModel;
import com.kamilcodemate.todoserver.service.TaskServiceImpl;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
public class UserPanelController {


    @Autowired
    CheckJwtToken checkJwtToken;

    @Autowired
    TaskServiceImpl taskService;





    @PostMapping("/api/user-panel/get-tasks-from-current-date")
    public ResponseEntity<List<Task>>  userPanel(@RequestBody GetAllTasksByDateAPIModel apiData)
    {
        System.out.println(apiData.toString());
       Claims tokenClaims = checkJwtToken.checkJwt(apiData.getToken());
        String role = tokenClaims.get("role").toString();

        if(role.equals("USER"))
        {
            List<Task> tasks = taskService.getTaskByDate(apiData.getDate(), apiData.getUsername());

            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
