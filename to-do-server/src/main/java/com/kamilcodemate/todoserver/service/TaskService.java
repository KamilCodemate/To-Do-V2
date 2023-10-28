package com.kamilcodemate.todoserver.service;

import com.kamilcodemate.todoserver.entity.Task;

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
 Task saveTask(Task task);

    /** Getting task list by particular day and username
     * @param date Day which tasks must be from
     * @param username Username of the user to whom the tasks belong
     * @return List of tasks
     */
 List<Task> getTaskByDate(LocalDate date, String username);

    /** Update Task importance by its id
     * @param isImportant Importance state
     * @param id Task id
     * @return Integer of id of updated task
     */
     Integer updateIsImportantTaskAttributeById(boolean isImportant, Long id);

    /** Getting all tasks for particular User username
     * @param username Username of the user to whom the tasks belong
     * @return List of User tasks
     */
     List<Task> getAllUserTasks(String username);
}
