package com.kamilcodemate.todoserver.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
    @NotNull(message = "User first name cannot be null")
    @NotBlank(message = "User first name cannot be blank")
    private String firstName;
    /**
     * Last name of a user
     */
    @Length(min = 2, max = 30)
    @NotNull(message = "User last name cannot be null")
    @NotBlank(message = "User last name cannot be blank")
    private String lastName;
    /**
     * Username of a user
     */
    @Length(min = 2, max = 15, message = "User username must contain between 2 and 15 characters")
    @NotNull(message = "User username cannot be null")
    @NotBlank(message = "User username cannot be blank")
    @Column(unique = true)
    private String username;
    /**
     * Password of a user
     */
    @NotNull(message = "User password cannot be null")
    @NotBlank(message = "User password cannot be blank")
    @Column(columnDefinition = "LONGTEXT")
    private String password;
    /**
     * APP ROLE
     */
    @NotNull(message = "user role cannot be null")
    private String role;
    /**
     * Corresponding List of {@link Task} for a user
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @ToString.Exclude
    List<Task> tasks;

}
