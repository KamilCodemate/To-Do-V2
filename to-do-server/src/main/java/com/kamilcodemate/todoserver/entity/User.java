package com.kamilcodemate.todoserver.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * User entity
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    /**
     * Unique id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    /**
     * First name of a user
     */
    @Length(min = 2, max = 20)
    @NotNull
    private String firstName;
    /**
     * Last name of a user
     */
    @Length(min = 2, max = 30)
    @NotNull
    private String lastName;
    /**
     * Username of a user
     */
    @Length(min = 2, max = 15)
    @NotNull
    @Column(unique = true)
    private String username;
    /**
     * Password of a user
     */
    @NotNull
    @Column(columnDefinition = "LONGTEXT")
    private String password;
    /**
     * APP ROLE
     */
    @NotNull
    private String role;
    /**
     * Corresponding List of {@link Task} for a user
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<Task> tasks;

}
