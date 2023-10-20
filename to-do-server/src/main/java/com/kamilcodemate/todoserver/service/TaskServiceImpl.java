package com.kamilcodemate.todoserver.service;

import com.kamilcodemate.todoserver.entity.Task;
import com.kamilcodemate.todoserver.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class TaskServiceImpl implements TaskService {

  @Autowired
    TaskRepository taskRepository;

  Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
    public Task saveTask(Task task)
    {
        return taskRepository.save(task);
    }

  @Override
  public List<Task> getTaskByDate(LocalDate date, String username){
      List<Task> tasks = taskRepository.findAll();


    return taskRepository.getTaskByDateAndUserUsername(date, username);
  }

    @Override
    public Integer updateIsImportantTaskAttributeById(boolean isImportant, Long id) {
        return taskRepository.updateIsImportantTaskAttributeById(isImportant, id);

    }

    @Override
    public List<Task> getAllUserTasks(String username) {

        return taskRepository.getTasksByUserUsername(username);
    }
}
