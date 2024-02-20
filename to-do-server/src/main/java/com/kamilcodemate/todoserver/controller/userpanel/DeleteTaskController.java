package com.kamilcodemate.todoserver.controller.userpanel;


import com.kamilcodemate.todoserver.entity.Task;
import com.kamilcodemate.todoserver.exception.InvalidTokenException;
import com.kamilcodemate.todoserver.model.TaskModels.DeleteTaskRequestModel;
import com.kamilcodemate.todoserver.service.TaskServiceImpl;
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
public class DeleteTaskController {

    private final static String TOKEN_HEADER = "Authorization";

    @Autowired
    private TaskServiceImpl taskService;

    @PostMapping("/api/userpanel/deleteTask")
    public ResponseEntity<List<Task>> getAllImportantTasks(@RequestBody @Valid DeleteTaskRequestModel requestData, @RequestHeader(name = TOKEN_HEADER) String token) throws InvalidTokenException {
        Integer retId = taskService.deleteTaskById(requestData.getTaskId(), requestData.getUsername(), token);

        if(retId != null) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
