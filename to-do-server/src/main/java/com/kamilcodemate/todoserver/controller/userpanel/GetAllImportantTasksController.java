package com.kamilcodemate.todoserver.controller.userpanel;

import com.kamilcodemate.todoserver.entity.Task;
import com.kamilcodemate.todoserver.exception.InvalidTokenException;
import com.kamilcodemate.todoserver.service.TaskServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetAllImportantTasksController {
    /**
     * Header field name for Bearer Token.
     */
    private static final String TOKEN_HEADER = "Authorization";

    /**
     * Service class for Task.
     */
    @Autowired
    TaskServiceImpl taskService;


    @PostMapping("/api/userpanel/getAllImportantTasks")
    public ResponseEntity<List<Task>> getAllImportantTasks(@RequestBody @Valid String  data, @RequestHeader(name = TOKEN_HEADER) String token) throws JSONException, InvalidTokenException {
        JSONObject jsonObject = new JSONObject(data);
        String username = jsonObject.getString("username");

        List<Task> tasks = taskService.getAllImportantTasks(username, token);

        if(tasks != null) return new ResponseEntity<>(tasks, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);


    }

}
