package com.kamilcodemate.todoserver.controller;

import com.kamilcodemate.todoserver.entity.Task;
import com.kamilcodemate.todoserver.entity.User;
import com.kamilcodemate.todoserver.exception.InvalidTokenException;
import com.kamilcodemate.todoserver.helpers.CheckJwtToken;
import com.kamilcodemate.todoserver.model.TaskModels.AddTaskRequestModel;
import com.kamilcodemate.todoserver.model.TaskModels.GetAllTasksByDateAPIModel;
import com.kamilcodemate.todoserver.model.TaskModels.UpdateTaskModel;
import com.kamilcodemate.todoserver.service.TaskServiceImpl;
import com.kamilcodemate.todoserver.service.UserServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class UserPanelController {


    @Autowired
    CheckJwtToken checkJwtToken;

    @Autowired
    TaskServiceImpl taskService;

    @Autowired
    UserServiceImpl userService;

    private static final String TOKEN_HEADER = "Authorization";



    @PostMapping("/api/user-panel/get-tasks-from-current-date")
    public ResponseEntity<List<Task>> getTasksFromCurrentDate(@RequestBody GetAllTasksByDateAPIModel requestData, @RequestHeader(name = TOKEN_HEADER) String token) throws InvalidTokenException {
        String clearedToken = token.replace("Bearer", "");
       Claims tokenClaims = checkJwtToken.checkJwt(clearedToken, requestData.getUsername());
        String role = tokenClaims.get("role").toString();

        if(role.equals("USER"))
        {
            List<Task> tasks = taskService.getTaskByDate(requestData.getDate(), requestData.getUsername());

            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/api/user-panel/get-tasks-from-current-date")
    public ResponseEntity<String> updateTasks(@RequestBody UpdateTaskModel requestData, @RequestHeader(name = TOKEN_HEADER) String token) throws InvalidTokenException {
        String clearedToken = token.replace("Bearer", "");



        Claims tokenClaims = checkJwtToken.checkJwt(clearedToken, requestData.getUsername());
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
    public ResponseEntity<?> addTask(@RequestBody AddTaskRequestModel requestData, @RequestHeader(name = TOKEN_HEADER) String token) throws InvalidTokenException {


    System.out.println("ADDTASK METHOD");
        String clearedToken = token.replace("Bearer", "");
        Claims tokenClaims = checkJwtToken.checkJwt(clearedToken, requestData.getUsername());
        String role = tokenClaims.get("role").toString();
        final String USERNAME = requestData.getUsername();

        User user = userService.findUserByUsername(USERNAME);
        if(role.equals("USER"))
        {
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
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    @PostMapping("/api/userpanel/getalltasks")
    public ResponseEntity<List<Task>> getAllTasks(@RequestBody String data, @RequestHeader(name = TOKEN_HEADER) String token) throws InvalidTokenException, JSONException {
        String clearedToken = token.replace("Bearer", "");
        JSONObject jsonObject = new JSONObject(data);
        String username = jsonObject.getString("username");

        System.out.println(username);
        Claims tokenClaims = checkJwtToken.checkJwt(clearedToken, username);

        final String ROLE = tokenClaims.get("role").toString();
        if(ROLE.equals("USER"))
        {
            List<Task> taskList = taskService.getAllUserTasks(username);
            return new ResponseEntity<>(taskList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);


    }

   


}
