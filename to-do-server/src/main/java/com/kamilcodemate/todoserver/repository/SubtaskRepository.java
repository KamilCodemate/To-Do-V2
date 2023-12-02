package com.kamilcodemate.todoserver.repository;

import com.kamilcodemate.todoserver.entity.Subtask;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtaskRepository extends JpaRepository<Subtask, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Subtask t SET t.isDone = :isDone WHERE t.id = :id " +
            "AND t.task.user.username = :username")
    Integer updateSubtaskCompletion(boolean isDone, Long id, String username);
}
