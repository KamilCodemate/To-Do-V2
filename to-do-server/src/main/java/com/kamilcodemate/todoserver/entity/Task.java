package com.kamilcodemate.todoserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

/**
 * Task entity
 */
@Entity
@Table(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Task {

    /**
     * Unique id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Name of task
     */
    @NotNull
    @Length(min = 1, max = 100)
    private String name;

    /**
     * Data (without time) for task deadline
     */
    private LocalDate date;
    /**
     * Time for task deadline
     */
    private Time time;
    /**
     * Task execution status (false - undone; true - done)
     */
    @NotNull
    private boolean isDone;
    /**
     * Task importance status (false - undone; true - done)
     */
    @NotNull
    private boolean isImportant;
    /**
     * Task description
     */
    private String description;
    /**
     * Corresponding List of {@link Subtask} for task
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<Subtask> subtasks;
    /**
     * User that task belongs to
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



}
