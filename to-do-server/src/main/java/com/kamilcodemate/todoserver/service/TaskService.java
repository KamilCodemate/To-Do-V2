package com.kamilcodemate.todoserver.service;

import com.kamilcodemate.todoserver.entity.Task;
import com.kamilcodemate.todoserver.entity.User;
import com.kamilcodemate.todoserver.exception.InvalidTokenException;
import com.kamilcodemate.todoserver.model.TaskModels.AddTaskRequestModel;

import java.time.LocalDate;
import java.util.List;

/**
 * Service Interface for {@link Task}
 */
public interface TaskService {


    /** Save task
     * @param task {@link Task} Object to save
     * @return Saved Task
     */
 Task saveTask(AddTaskRequestModel taskModel, String token, User user) throws InvalidTokenException;

    /**
     * Getting task list by particular day and username
     *
     * @param date     Day which tasks must be from
     * @param username Username of the user to whom the tasks belong
     * @param token
     * @return List of tasks
     */
 List<Task> getTaskByDate(LocalDate date, String username, String token) throws InvalidTokenException;

    /**
     * Update Task importance by its id
     *
     * @param isImportant Importance state
     * @param id          Task id
     * @param username
     * @param token
     * @return Integer of id of updated task
     */
     Integer updateIsImportantTaskAttributeById(boolean isImportant, Long id, String username, String token) throws InvalidTokenException;

    /** Getting all tasks for particular User username
     * @param username Username of the user to whom the tasks belong
     * @return List of User tasks
     */
     List<Task> getAllUserTasks(String username, String token) throws InvalidTokenException;
}
