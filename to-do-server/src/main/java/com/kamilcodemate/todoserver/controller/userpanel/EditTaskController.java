package com.kamilcodemate.todoserver.controller.userpanel;
import com.kamilcodemate.todoserver.entity.Task;
import com.kamilcodemate.todoserver.exception.InvalidTokenException;
import com.kamilcodemate.todoserver.model.TaskModels.EditTaskRequestModel;
import com.kamilcodemate.todoserver.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class EditTaskController {
    private final static String TOKEN_HEADER = "Authorization";

    @Autowired
    private TaskServiceImpl taskService;

    @PostMapping("/api/userpanel/edittask")
    public ResponseEntity<List<Task>> getAllImportantTasks(@RequestBody EditTaskRequestModel requestData, @RequestHeader(name = TOKEN_HEADER) String token) throws InvalidTokenException {
        System.out.println("edit request data = " + requestData);
        Integer retId = taskService.updateTaskById(requestData.getTaskId(), requestData.getUsername(), requestData, token);

        if(retId != null) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
