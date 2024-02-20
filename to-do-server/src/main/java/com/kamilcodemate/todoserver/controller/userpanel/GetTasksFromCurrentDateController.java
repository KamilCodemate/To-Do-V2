package com.kamilcodemate.todoserver.controller.userpanel;

import com.kamilcodemate.todoserver.entity.Task;
import com.kamilcodemate.todoserver.exception.InvalidTokenException;
import com.kamilcodemate.todoserver.model.TaskModels.GetAllTasksByDateAPIModel;
import com.kamilcodemate.todoserver.service.SubtaskServiceImpl;
import com.kamilcodemate.todoserver.service.TaskServiceImpl;
import com.kamilcodemate.todoserver.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class GetTasksFromCurrentDateController {

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
     * Getting all tasks from particular day.
     *
     * @param requestData Data from JSON API
     * @param token       Barer token from Authorization Header
     * @return ResponseEntity containing HTTPStatus and list of tasks
     * if successful
     * @throws InvalidTokenException Throws if token is invalid
     */
    @PostMapping("/api/userpanel/get-tasks-from-current-date")
    public ResponseEntity<List<Task>> getTasksFromCurrentDate (@RequestBody GetAllTasksByDateAPIModel requestData, @RequestHeader(name = TOKEN_HEADER) String token) throws InvalidTokenException {
        List<Task> tasks = taskService.getTaskByDate(requestData.getDate(),
                requestData.getUsername(), token);

        if(tasks != null) return new ResponseEntity<>(tasks, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

}
