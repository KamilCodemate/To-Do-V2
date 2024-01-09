package com.kamilcodemate.todoserver.repository;

import com.kamilcodemate.todoserver.entity.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

/**
 * Task Repository
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /** Getting tasks from particular day for particular user
     * @param date The day from which tasks must come
     * @param username Username of the user who owns the tasks
     * @return List of tasks
     */
     List<Task> getTaskByDateAndUserUsername(LocalDate date, String username);

    /** Getting all tasks for particular user
     * @param username Username of the user who owns the tasks
     * @return List of tasks
     */
     List<Task> getTasksByUserUsername(String username);
    List<Task> getTasksByUserUsernameAndIsImportantTrue(String username);
    List<Task> getTasksByUserUsernameAndIsDoneTrue(String username);

    /** Updating Task importance
     * @param isImportant Task importance state
     * @param id Id of Task to update
     * @return Integer of updated Task
     */
    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.isImportant = :isImportant WHERE t.id = :id " +
            "AND t.user.username = :username")
     Integer updateTaskImportance(boolean isImportant, Long id
            , String username);

    /**
     * Updating Task completion state
     * @param isDone Task completion state
     * @param id Id of Task to update
     * @param username Username of the user who owns the Task
     * @return Integer of updated Task
     */
    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.isDone = :isDone WHERE t.id = :id " +
            "AND t.user.username = :username")
   Integer updateTaskCompletion(boolean isDone, Long id, String username);


    @Modifying
    @Transactional
    @Query("DELETE Task t WHERE t.id = :id AND t.user.username = :username")
    Long deleteTaskById(Long id, String username);

    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.name = :name, t.description = :description, t.isImportant = :isImportant, t.isDone = :isDone, t.date = :date, t.startTime = :startTime, t.endTime = :endTime WHERE t.id = :id AND t.user.username = :username")
    Long editTask(Long id, String username, String name, String description, boolean isImportant, boolean isDone, LocalDate date, Time startTime, Time endTime);



}
