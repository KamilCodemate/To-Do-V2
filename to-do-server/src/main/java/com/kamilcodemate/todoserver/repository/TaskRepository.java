package com.kamilcodemate.todoserver.repository;

import com.kamilcodemate.todoserver.entity.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    public List<Task> getTaskByDateAndUserUsername(LocalDate date, String username);
    public List<Task> getTasksByUserUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.isImportant = :isImportant WHERE t.id = :id")
    public Integer updateIsImportantTaskAttributeById(boolean isImportant, Long id);
}
