package com.kamilcodemate.todoserver.controller;

import com.kamilcodemate.todoserver.entity.Task;
import com.kamilcodemate.todoserver.entity.User;
import com.kamilcodemate.todoserver.helpers.CheckJwtToken;
import com.kamilcodemate.todoserver.model.TaskModels.AddTaskRequestModel;
import com.kamilcodemate.todoserver.service.TaskServiceImpl;
import com.kamilcodemate.todoserver.service.UserServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Autowired
    CheckJwtToken checkJwtToken;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    TaskServiceImpl taskService;

    @PostMapping("/api/addtask")
    public ResponseEntity<?> addTask(@RequestBody AddTaskRequestModel data)
    {
        Claims tokenClaims = checkJwtToken.checkJwt(data.getToken());
        final String ROLE = tokenClaims.get("role").toString();
        final String USERNAME = data.getUsername();

        User user = userService.findUserByUsername(USERNAME);
        if(user == null) throw new EntityNotFoundException("No user found");

        Task task = new Task();
        task.setName(data.getName());
        task.setDate(null);
        task.setDone(false);
        task.setImportant(false);
        task.setSubtasks(null);
        task.setUser(user);

       Task responseTask = taskService.saveTask(task);
       if(responseTask == null) throw new InternalError("An error occurred on our side. Please try again later");

       return new ResponseEntity<>("Task saved.", HttpStatus.OK);
    }
}
