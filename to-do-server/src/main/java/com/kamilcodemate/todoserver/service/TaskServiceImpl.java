package com.kamilcodemate.todoserver.service;

import com.kamilcodemate.todoserver.entity.Task;
import com.kamilcodemate.todoserver.entity.User;
import com.kamilcodemate.todoserver.exception.InvalidTokenException;
import com.kamilcodemate.todoserver.helpers.CheckJwtToken;
import com.kamilcodemate.todoserver.model.TaskModels.AddTaskRequestModel;
import com.kamilcodemate.todoserver.repository.TaskRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service class for Task (implements {@link TaskService})
 */
@Service

public class TaskServiceImpl implements TaskService {

    /**
     * Bearer token helper methods
     */
    @Autowired
    CheckJwtToken checkJwtToken;

    /**
     * Task Repository class
     */
    @Autowired
    final TaskRepository taskRepository;


    /** IOC constructor
     * @param taskRepository TaskRepository to initialize
     */
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * @param taskModel {@link AddTaskRequestModel} Model contains info to
     * save
     * @param token Bearer token
     * @param user User of Task
     * @return Saved Task
     */
    public Task saveTask(AddTaskRequestModel taskModel, String token, User user) throws InvalidTokenException {
        String clearedToken = token.replace("Bearer", "");
        Claims tokenClaims = checkJwtToken.checkJwt(clearedToken, user.getUsername());
        String role = tokenClaims.get("role").toString();

        if (role.equals("USER")) {
            Task task = new Task();
            task.setName(taskModel.getName());
            task.setDescription(taskModel.getDescription());
            task.setTime(taskModel.getTime());
            task.setDone(false);
            task.setImportant(false);
            task.setUser(user);
            task.setSubtasks(taskModel.getSubtasks());
            return taskRepository.save(task);
        }
        throw new InvalidTokenException("Invalid Token");
    }



    /**
     * @param date     Day which tasks must be from
     * @param username Username of the user to whom the tasks belong
     * @param token
     * @return List of tasks
     */
  @Override
  public List<Task> getTaskByDate(LocalDate date, String username, String token) throws InvalidTokenException {
      String clearedToken = token.replace("Bearer", "");
      Claims tokenClaims = checkJwtToken.checkJwt
              (clearedToken, username);
      String role = tokenClaims.get("role").toString();

      if (role.equals("USER")) {
          return taskRepository.getTaskByDateAndUserUsername(date, username);


      }
     return null;


  }

    /**
     * @param isImportant Importance state
     * @param id          Task id
     * @param username
     * @param token
     * @return Integer of an updated Task id
     */
    @Override
    public Integer updateIsImportantTaskAttributeById(boolean isImportant, Long id, String username, String token) throws InvalidTokenException {

        String clearedToken = token.replace("Bearer", "");
        Claims tokenClaims = checkJwtToken.checkJwt
                (clearedToken, username);
        String role = tokenClaims.get("role").toString();
        if (role.equals("USER")) {
            return taskRepository.updateIsImportantTaskAttributeById(isImportant, id, username);
        }
         return null;

    }

    /** Getting all tasks for username
     * @param username Username of the user to whom the tasks belong
     * @return List of tasks
     */
    @Override
    public List<Task> getAllUserTasks(String username, String token) throws InvalidTokenException {
        String clearedToken = token.replace("Bearer", "");

        Claims tokenClaims = checkJwtToken.checkJwt(clearedToken, username);
        final String ROLE = tokenClaims.get("role").toString();


        if (ROLE.equals("USER")) {
            return taskRepository.getTasksByUserUsername(username);
        }
        throw new InvalidTokenException("Invalid token");

    }
}
