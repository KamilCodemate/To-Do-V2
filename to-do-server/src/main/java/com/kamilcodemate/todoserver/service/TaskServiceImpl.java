package com.kamilcodemate.todoserver.service;

import com.kamilcodemate.todoserver.entity.Subtask;
import com.kamilcodemate.todoserver.entity.Task;
import com.kamilcodemate.todoserver.entity.User;
import com.kamilcodemate.todoserver.exception.InvalidTokenException;
import com.kamilcodemate.todoserver.helpers.CheckJwtToken;
import com.kamilcodemate.todoserver.model.TaskModels.AddTaskRequestModel;
import com.kamilcodemate.todoserver.model.TaskModels.EditTaskRequestModel;
import com.kamilcodemate.todoserver.repository.TaskRepository;
import com.kamilcodemate.todoserver.repository.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
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

    final TaskRepository taskRepository;


    final UserRepository userRepository;

    /** IOC constructor
     * @param taskRepository TaskRepository to initialize
     */
    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
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
            task.setDate(taskModel.getDate());
            task.setDescription(taskModel.getDescription());
            task.setStartTime(taskModel.getStartTime());
            task.setEndTime(taskModel.getEndTime());
            task.setDone(false);
            task.setImportant(false);
            task.setUser(user);

            List<Subtask> subtasks = taskModel.getSubtasks();
            for(Subtask subtask : subtasks) {
                subtask.setTask(task);
            }
            task.setSubtasks(subtasks);

            System.out.println(task);

            return taskRepository.save(task);
        }
        throw new InvalidTokenException("Invalid Token");
    }




    /**
     * @param date     Day which tasks must be from
     * @param username Username of the user to whom the tasks belong
     * @param token Bearer Token
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
      throw new InvalidTokenException("Invalid permission role");


  }

    /**
     * @param isImportant Importance state
     * @param id          Task id
     * @param username  Username of the user to whom the tasks belong
     * @param token Bearer Token
     * @return Integer of an updated Task id
     */
    @Override
    public Integer updateIsImportantTaskAttributeById(boolean isImportant, Long id, String username, String token) throws InvalidTokenException {

        String clearedToken = token.replace("Bearer", "");
        Claims tokenClaims = checkJwtToken.checkJwt
                (clearedToken, username);
        String role = tokenClaims.get("role").toString();
        if (role.equals("USER")) {
            return taskRepository.updateTaskImportance(isImportant, id, username);
        }
        throw new InvalidTokenException("Invalid permission role");

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
        throw new InvalidTokenException("Invalid permission role");

    }

    @Override
    public Integer updateTaskCompletion(boolean isDone, Long id, String username, String token) throws InvalidTokenException {
        String clearedToken = token.replace("Bearer", "");
        Claims tokenClaims = checkJwtToken.checkJwt(clearedToken, username);
        final String ROLE = tokenClaims.get("role").toString();


        if (ROLE.equals("USER")) {
            return taskRepository.updateTaskCompletion(isDone, id, username);
        }
        throw new InvalidTokenException("Invalid permission role");
    }

    @Override
    public List<Task> getAllImportantTasks(String username, String token) throws InvalidTokenException {
        String clearedToken = token.replace("Bearer", "");
        Claims tokenClaims = checkJwtToken.checkJwt(clearedToken, username);
        final String ROLE = tokenClaims.get("role").toString();


        if(ROLE.equals("USER"))
        {
            return taskRepository.getTasksByUserUsernameAndIsImportantTrue(username);
        }
        throw new InvalidTokenException("Invalid permission role");
    }

    @Override
    public List<Task> getAllCompletedTasks(String username, String token) throws InvalidTokenException {
        String clearedToken = token.replace("Bearer", "");
        Claims tokenClaims = checkJwtToken.checkJwt(clearedToken, username);
        final String ROLE = tokenClaims.get("role").toString();

        if(ROLE.equals("USER"))
        {
            return taskRepository.getTasksByUserUsernameAndIsDoneTrue(username);
        }
        throw new InvalidTokenException("Invalid permission role");
    }

    @Override
    public Integer deleteTaskById(Long taskId, String username, String token) throws InvalidTokenException {
        String clearedToken = token.replace("Bearer", "");
        Claims tokenClaims = checkJwtToken.checkJwt(clearedToken, username);
        final String ROLE = tokenClaims.get("role").toString();

        if(ROLE.equals("USER"))
        {
            return taskRepository.deleteTaskById(taskId, username);
        }
        throw new InvalidTokenException("Invalid permission role");
    }

    @Override
    public Long updateTaskById(Long taskId, String username, EditTaskRequestModel taskData, String token) throws InvalidTokenException {
        String clearedToken = token.replace("Bearer", "");
        Claims tokenClaims = checkJwtToken.checkJwt(clearedToken, username);
        final String ROLE = tokenClaims.get("role").toString();

        if(ROLE.equals("USER"))
        {
            final User user = userRepository.getUserByUsername(taskData.getUsername());
            if(user == null) throw new BadCredentialsException("Invalid user data");
            Task task = getTask(taskData, user);

            System.out.println(task);
            Task retTask = taskRepository.save(task);
            return retTask.getId();
        }

        throw new InvalidTokenException("Invalid permission role");
    }

    private static Task getTask(EditTaskRequestModel taskData, User user) {
        Task task = new Task();
        task.setUser(user);
        task.setId(taskData.getTaskId());
        task.setName(taskData.getName());
        task.setDescription(taskData.getDescription());
        task.setImportant(taskData.isImportant());
        task.setDone(taskData.isDone());
        task.setDate(taskData.getDate());
        task.setStartTime(taskData.getStartTime());
        task.setEndTime(taskData.getEndTime());


        List<Subtask> subtasks = taskData.getSubtasks();
        subtasks.forEach(subtask -> subtask.setTask(task));
        task.setSubtasks(subtasks);
        System.out.println(task);
        return task;
    }


}
