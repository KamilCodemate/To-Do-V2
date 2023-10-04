package com.kamilcodemate.todoserver.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Length(min = 2, max = 20)
    @NotNull
    private String firstName;

    @Length(min = 2, max = 30)
    @NotNull
    private String lastName;

    @Length(min = 2, max = 15)
    @NotNull
    private String username;

    @NotNull
    @Column(columnDefinition = "LONGTEXT")
    private String password;

    @NotNull
    private String role;

}
