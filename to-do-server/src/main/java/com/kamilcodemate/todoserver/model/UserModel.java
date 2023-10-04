package com.kamilcodemate.todoserver.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
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

    @Length(min = 8, max = 25)
    @NotNull

    private String password;

    @Length(min = 8, max = 25)
    @NotNull
    private String confirmPassword;

    @NotNull
    private String role;


}
