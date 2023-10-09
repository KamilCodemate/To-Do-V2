package com.kamilcodemate.todoserver.service;

import com.kamilcodemate.todoserver.entity.Task;
import com.kamilcodemate.todoserver.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

  @Autowired
    TaskRepository taskRepository;

    public Task saveTask(Task task)
    {
        return taskRepository.save(task);
    }
}
