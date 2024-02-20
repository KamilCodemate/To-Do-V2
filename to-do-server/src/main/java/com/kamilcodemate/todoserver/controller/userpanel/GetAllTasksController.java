package com.kamilcodemate.todoserver.controller.userpanel;

import com.kamilcodemate.todoserver.entity.Task;
import com.kamilcodemate.todoserver.exception.InvalidTokenException;
import com.kamilcodemate.todoserver.service.SubtaskServiceImpl;
import com.kamilcodemate.todoserver.service.TaskServiceImpl;
import com.kamilcodemate.todoserver.service.UserServiceImpl;
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
public class GetAllTasksController {
    /**
     * Header field name for Bearer Token.
     */
    private static final String TOKEN_HEADER = "Authorization";

    /**
     * Service class for Task.
     */
    @Autowired
    TaskServiceImpl taskService;




    /**
     * Getting all tasks by username.
     *
     * @param data  Data from JSON API
     * @param token Bearer token from Authorization Header
     * @return ResponseEntity containing HTTPStatus and optional message and
     * list of tasks if successful
     * @throws InvalidTokenException Throws if token is invalid
     * @throws JSONException         Throws if JSON API data is invalid
     */
    @PostMapping("/api/userpanel/getalltasks")
    public ResponseEntity<List<Task>>
    getAllTasks(@RequestBody @Valid String data, @RequestHeader(name = TOKEN_HEADER) String token) throws InvalidTokenException, JSONException {
        JSONObject jsonObject = new JSONObject(data);
        String username = jsonObject.getString("username");

        List<Task> taskList = taskService.getAllUserTasks(username, token);
        if(taskList != null) return new ResponseEntity<>(taskList,
                HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);


    }
}
