package com.kamilcodemate.todoserver.controller.userpanel;


import com.kamilcodemate.todoserver.entity.Task;
import com.kamilcodemate.todoserver.entity.User;
import com.kamilcodemate.todoserver.exception.InvalidTokenException;
import com.kamilcodemate.todoserver.model.TaskModels.AddTaskRequestModel;
import com.kamilcodemate.todoserver.service.SubtaskServiceImpl;
import com.kamilcodemate.todoserver.service.TaskServiceImpl;
import com.kamilcodemate.todoserver.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddTaskController {

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
     * Service class for user.
     */
    @Autowired
    UserServiceImpl userService;



    /**
     * Adding new task by username.
     *
     * @param requestData Data from JSON API
     * @param token       Bearer token from Authorization Header
     * @return ResponseEntity containing HTTPStatus and optional message
     * @throws InvalidTokenException Throws if token is invalid
     */
    @PostMapping("/api/userpanel/addtask") public ResponseEntity<?> addTask
    (@RequestBody AddTaskRequestModel requestData,
     @RequestHeader(name = TOKEN_HEADER) String token)
            throws InvalidTokenException {
        User user = userService.findUserByUsername(requestData.getUsername()
                , token);

        Task responseTask = taskService.saveTask(requestData, token, user);
        if (responseTask == null)
            throw new InternalError("An error occurred on our side. " +
                    "Please try again later");

        return new ResponseEntity<>("Task saved.", HttpStatus.OK);
    }

}
