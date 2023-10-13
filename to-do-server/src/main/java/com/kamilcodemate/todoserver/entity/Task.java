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
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(min = 1, max = 100)
    private String name;


    private LocalDate date;
    private Time time;
    @NotNull
    private boolean isDone;
    @NotNull
    private boolean isImportant;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<Subtasks> subtasks;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
