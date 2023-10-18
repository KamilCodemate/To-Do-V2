package com.kamilcodemate.todoserver.service;

import com.kamilcodemate.todoserver.entity.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {

public Task saveTask(Task task);
public List<Task> getTaskByDate(LocalDate date, String username);
    public Integer updateIsImportantTaskAttributeById(boolean isImportant, Long id);
}
