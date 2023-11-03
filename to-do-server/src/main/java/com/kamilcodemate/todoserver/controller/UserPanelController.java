package com.kamilcodemate.todoserver.controller;

import com.kamilcodemate.todoserver.entity.Task;
import com.kamilcodemate.todoserver.entity.User;
import com.kamilcodemate.todoserver.exception.InvalidTokenException;
import com.kamilcodemate.todoserver.model.TaskModels.AddTaskRequestModel;
import com.kamilcodemate.todoserver.model.TaskModels.GetAllTasksByDateAPIModel;
import com.kamilcodemate.todoserver.model.TaskModels.UpdateTaskCompletionModel;
import com.kamilcodemate.todoserver.model.TaskModels.UpdateTaskImportanceModel;
import com.kamilcodemate.todoserver.service.TaskServiceImpl;
import com.kamilcodemate.todoserver.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * A set of controllers to operate the user panel.
 */
@RestController
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPanelController {


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
     * Getting all tasks from particular day.
     *
     * @param requestData Data from JSON API
     * @param token       Barer token from Authorization Header
     * @return ResponseEntity containing HTTPStatus and list of tasks
     * if successful
     * @throws InvalidTokenException Throws if token is invalid
     */
    @PostMapping("/api/user-panel/get-tasks-from-current-date")
    public ResponseEntity<List<Task>> getTasksFromCurrentDate (@RequestBody GetAllTasksByDateAPIModel requestData, @RequestHeader(name = TOKEN_HEADER) String token) throws InvalidTokenException {
        List<Task> tasks = taskService.getTaskByDate(requestData.getDate(),
                requestData.getUsername(), token);
        if(tasks != null) return new ResponseEntity<>(tasks, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

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

        @PutMapping("/api/user-panel/updatetaskcompletion")
        public ResponseEntity<String> updateTaskCompletion(@RequestBody UpdateTaskCompletionModel requestData, @RequestHeader(name=TOKEN_HEADER) String token) throws InvalidTokenException
        {
            System.out.println("RequestData = " + requestData);
            Integer retTask = taskService.updateTaskCompletion(requestData.isDone(), requestData.getTaskId(), requestData.getUsername(), token
            );
            if (retTask != null ) return new ResponseEntity<>("Task updated.", HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }



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
    getAllTasks(@RequestBody String data, @RequestHeader(name = TOKEN_HEADER) String token) throws InvalidTokenException, JSONException {
        JSONObject jsonObject = new JSONObject(data);
        String username = jsonObject.getString("username");

        List<Task> taskList = taskService.getAllUserTasks(username, token);
        if(taskList != null) return new ResponseEntity<>(taskList,
                HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);


    }




}
