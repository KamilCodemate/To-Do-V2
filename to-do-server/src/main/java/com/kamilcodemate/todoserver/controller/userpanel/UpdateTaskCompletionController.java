package com.kamilcodemate.todoserver.controller.userpanel;


import com.kamilcodemate.todoserver.exception.InvalidTokenException;
import com.kamilcodemate.todoserver.model.TaskModels.UpdateTaskCompletionModel;
import com.kamilcodemate.todoserver.service.SubtaskServiceImpl;
import com.kamilcodemate.todoserver.service.TaskServiceImpl;
import com.kamilcodemate.todoserver.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateTaskCompletionController {
    /**
     * Header field name for Bearer Token.
     */
    private static final String TOKEN_HEADER = "Authorization";
    /**
     * Service class for Task.
     */
    @Autowired
    TaskServiceImpl taskService;




    @PutMapping("/api/user-panel/updatetaskcompletion")
    public ResponseEntity<String> updateTaskCompletion(@RequestBody UpdateTaskCompletionModel requestData, @RequestHeader(name=TOKEN_HEADER) String token) throws InvalidTokenException
    {
        System.out.println("RequestData = " + requestData);
        Integer retTask = taskService.updateTaskCompletion(requestData.isDone(), requestData.getTaskId(), requestData.getUsername(), token
        );
        if (retTask != null ) return new ResponseEntity<>("Task updated.", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
