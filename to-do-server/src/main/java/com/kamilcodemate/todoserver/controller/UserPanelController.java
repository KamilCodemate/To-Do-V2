package com.kamilcodemate.todoserver.controller;

import com.kamilcodemate.todoserver.entity.Task;
import com.kamilcodemate.todoserver.entity.User;
import com.kamilcodemate.todoserver.exception.InvalidTokenException;
import com.kamilcodemate.todoserver.helpers.CheckJwtToken;
import com.kamilcodemate.todoserver.model.AuthorizationRequestModel;
import com.kamilcodemate.todoserver.model.TaskModels.AddTaskRequestModel;
import com.kamilcodemate.todoserver.model.TaskModels.GetAllTasksByDateAPIModel;
import com.kamilcodemate.todoserver.model.TaskModels.UpdateTaskModel;
import com.kamilcodemate.todoserver.service.TaskServiceImpl;
import com.kamilcodemate.todoserver.service.UserServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
public class UserPanelController {


    @Autowired
    CheckJwtToken checkJwtToken;

    @Autowired
    TaskServiceImpl taskService;

    @Autowired
    UserServiceImpl userService;





    @PostMapping("/api/user-panel/get-tasks-from-current-date")
    public ResponseEntity<List<Task>> getTasksFromCurrentDate(@RequestBody GetAllTasksByDateAPIModel requestData) throws InvalidTokenException {

       Claims tokenClaims = checkJwtToken.checkJwt(requestData.getToken(), requestData.getUsername());
        String role = tokenClaims.get("role").toString();

        if(role.equals("USER"))
        {
            List<Task> tasks = taskService.getTaskByDate(requestData.getDate(), requestData.getUsername());

            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/api/user-panel/get-tasks-from-current-date")
    public ResponseEntity<String> updateTasks(@RequestBody UpdateTaskModel requestData) throws InvalidTokenException {
        System.out.println("Update data = "+requestData);
        final Long ID = requestData.getTaskId();
        final String TOKEN = requestData.getToken();
        final String USERNAME = requestData.getUsername();

        Claims tokenClaims = checkJwtToken.checkJwt(TOKEN, requestData.getUsername());
        String role = tokenClaims.get("role").toString();
        if(role.equals("USER"))
        {
            Integer retTask = taskService.updateIsImportantTaskAttributeById(requestData.isImportant(), requestData.getTaskId());

            if(retTask == null) return new ResponseEntity<>("Task on given id does not exist", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>("Task updated.", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @PostMapping("/api/userpanel/addtask")
    public ResponseEntity<?> addTask(@RequestBody AddTaskRequestModel requestData) throws InvalidTokenException {

        Claims tokenClaims = checkJwtToken.checkJwt(requestData.getToken(), requestData.getUsername());
        final String ROLE = tokenClaims.get("role").toString();
        final String USERNAME = requestData.getUsername();

        User user = userService.findUserByUsername(USERNAME);
        if(user == null) throw new EntityNotFoundException("No user found");

        Task task = new Task();
        task.setName(requestData.getName());
        task.setDate(null);
        task.setDone(false);
        task.setImportant(false);
        task.setSubtasks(null);
        task.setUser(user);

        Task responseTask = taskService.saveTask(task);
        if(responseTask == null) throw new InternalError("An error occurred on our side. Please try again later");

        return new ResponseEntity<>("Task saved.", HttpStatus.OK);
    }

    @PostMapping("/api/userpanel/getalltasks")
    public ResponseEntity<List<Task>> getAllTasks(@RequestBody AuthorizationRequestModel requestData) throws InvalidTokenException {
        final String TOKEN = requestData.getToken();
        final String USERNAME = requestData.getUsername();
        Claims tokenClaims = checkJwtToken.checkJwt(requestData.getToken(), USERNAME);
        final String ROLE = tokenClaims.get("role").toString();
        if(ROLE.equals("USER"))
        {
            List<Task> taskList = taskService.getAllUserTasks(USERNAME);
            return new ResponseEntity<>(taskList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);


    }

   


}
