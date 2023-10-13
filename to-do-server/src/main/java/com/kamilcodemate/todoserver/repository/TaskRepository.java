package com.kamilcodemate.todoserver.repository;

import com.kamilcodemate.todoserver.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    public List<Task> getTaskByDateAndUserUsername(LocalDate date, String username);

}
