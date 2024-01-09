package com.kamilcodemate.todoserver.controller.userpanel;

import com.kamilcodemate.todoserver.exception.InvalidTokenException;
import com.kamilcodemate.todoserver.model.TaskModels.UpdateTaskImportanceModel;
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
public class UpdateTaskImportanceController {

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
     * Updating existing task.
     *
     * @param requestData Data from JSON API
     * @param token       Bearer token from Authorization Header
     * @return ResponseEntity containing HTTPStatus and optional message
     * @throws InvalidTokenException Throws if token is invalid
     */
    @PutMapping("/api/user-panel/updatetaskimportance")
    public ResponseEntity<String> updateTaskImportance(@RequestBody UpdateTaskImportanceModel requestData,
                                                       @RequestHeader(name = TOKEN_HEADER) String token)
            throws InvalidTokenException {
        Integer retTask = taskService.updateIsImportantTaskAttributeById

                (requestData.isImportant(), requestData.getTaskId(),
                        requestData.getUsername(), token );
        if (retTask != null ) return new ResponseEntity<>("Task updated.", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
