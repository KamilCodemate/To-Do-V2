package com.kamilcodemate.todoserver.service;

import com.kamilcodemate.todoserver.entity.Task;
import com.kamilcodemate.todoserver.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service class for Task (implements {@link TaskService})
 */
@Service

public class TaskServiceImpl implements TaskService {

    /**
     * Task Repository class
     */
  final
  TaskRepository taskRepository;


    /** IOC contructor
     * @param taskRepository TaskRepository to initialize
     */
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * @param task {@link Task} Object to save
     * @return Saved Task
     */
    public Task saveTask(Task task)
    {
        return taskRepository.save(task);
    }

    /**
     * @param date     Day which tasks must be from
     * @param username Username of the user to whom the tasks belong
     * @return List of tasks
     */
  @Override
  public List<Task> getTaskByDate(LocalDate date, String username){


      return taskRepository.getTaskByDateAndUserUsername(date, username);
  }

    /**
     * @param isImportant Importance state
     * @param id          Task id
     * @return Integer of an updated Task id
     */
    @Override
    public Integer updateIsImportantTaskAttributeById(boolean isImportant, Long id) {
        return taskRepository.updateIsImportantTaskAttributeById(isImportant, id);

    }

    /** Getting all tasks for username
     * @param username Username of the user to whom the tasks belong
     * @return List of tasks
     */
    @Override
    public List<Task> getAllUserTasks(String username) {

        return taskRepository.getTasksByUserUsername(username);
    }
}
