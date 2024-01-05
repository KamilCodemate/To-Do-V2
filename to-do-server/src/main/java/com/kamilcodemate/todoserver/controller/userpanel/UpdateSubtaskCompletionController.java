package com.kamilcodemate.todoserver.controller.userpanel;

import com.kamilcodemate.todoserver.exception.InvalidTokenException;
import com.kamilcodemate.todoserver.model.TaskModels.UpdateSubtaskCompletionModel;
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
public class UpdateSubtaskCompletionController {

    /**
     * Header field name for Bearer Token.
     */
    private static final String TOKEN_HEADER = "Authorization";

    /**
     * Subtask service class for Task
     */
    @Autowired
    SubtaskServiceImpl subtaskService;

    @PutMapping("/api/userpanel/updatesubtaskcompletion")
    public ResponseEntity<String> updateSubtaskCompetion(@RequestBody UpdateSubtaskCompletionModel requestData, @RequestHeader(name = TOKEN_HEADER) String token) throws InvalidTokenException {
        Integer retTask = subtaskService.updateSubtaskCompletion(requestData.getUsername(), token, requestData.getSubtaskId(), requestData.isDone());

        if(retTask == null)
            throw new InternalError("An error occurred on our side. " +
                    "Please try again later");

        return new ResponseEntity<>("Subtask updated.", HttpStatus.OK);
    }
}
