package com.kamilcodemate.todoserver.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
public class Subtask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(min = 1, max = 100)
    private String name;

    @NotNull
    private boolean isDone;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
}
