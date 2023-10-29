package com.kamilcodemate.todoserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * Subtask entity
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subtasks")
public class Subtask {

    /**
     * Unique id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of subtask
     */
    @NotNull
    @Length(min = 1, max = 100)
    private String name;

    /**
     * Subtask execution status (false - undone; true - done)
     */
    @NotNull
    private boolean isDone;

    /**
     * Corresponding {@link Task} class
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
}
