package com.kamilcodemate.todoserver.service;

import com.kamilcodemate.todoserver.entity.Task;
import com.kamilcodemate.todoserver.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
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
}
