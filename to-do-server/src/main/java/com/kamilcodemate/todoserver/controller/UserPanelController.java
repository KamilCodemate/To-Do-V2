package com.kamilcodemate.todoserver.controller;

import com.kamilcodemate.todoserver.entity.Task;
import com.kamilcodemate.todoserver.helpers.CheckJwtToken;
import com.kamilcodemate.todoserver.model.TaskModels.GetAllTasksByDateAPIModel;
import com.kamilcodemate.todoserver.model.TaskModels.UpdateTaskModel;
import com.kamilcodemate.todoserver.service.TaskServiceImpl;
import io.jsonwebtoken.Claims;
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





    @PostMapping("/api/user-panel/get-tasks-from-current-date")
    public ResponseEntity<List<Task>> getTasksFromCurrentDate(@RequestBody GetAllTasksByDateAPIModel apiData)
    {

       Claims tokenClaims = checkJwtToken.checkJwt(apiData.getToken());
        String role = tokenClaims.get("role").toString();

        if(role.equals("USER"))
        {
            List<Task> tasks = taskService.getTaskByDate(apiData.getDate(), apiData.getUsername());

            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/api/user-panel/get-tasks-from-current-date")
    public ResponseEntity<String> updateTasks(@RequestBody UpdateTaskModel updateData){
        System.out.println("Update data = "+updateData);
        final Long ID = updateData.getTaskId();
        final String TOKEN = updateData.getToken();
        final String USERNAME = updateData.getUsername();

        Claims tokenClaims = checkJwtToken.checkJwt(TOKEN);
        String role = tokenClaims.get("role").toString();
        if(role.equals("USER"))
        {
            Integer retTask = taskService.updateIsImportantTaskAttributeById(updateData.isImportant(), updateData.getTaskId());

            if(retTask == null) return new ResponseEntity<>("Task on given id does not exist", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>("Task updated.", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
