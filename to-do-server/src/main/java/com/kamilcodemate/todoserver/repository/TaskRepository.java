package com.kamilcodemate.todoserver.repository;

import com.kamilcodemate.todoserver.entity.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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

    /** Updating task importance
     * @param isImportant Task importance state
     * @param id Id of task to update
     * @return Integer of an task id
     */
    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.isImportant = :isImportant WHERE t.id = :id " +
            "AND t.user.username = :username")
     Integer updateIsImportantTaskAttributeById(boolean isImportant, Long id
            , String username);
}
