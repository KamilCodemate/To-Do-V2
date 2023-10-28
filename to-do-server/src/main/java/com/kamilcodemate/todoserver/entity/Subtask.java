package com.kamilcodemate.todoserver.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Subtask entity
 */
@Entity
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
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
}
