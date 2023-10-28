package com.kamilcodemate.todoserver.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


/**
 * User register request model
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    /**
     * User id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    /**
     * User first name
     */
    @Length(min = 2, max = 20)
    @NotNull
    private String firstName;

    /**
     * User last name
     */
    @Length(min = 2, max = 30)
    @NotNull
    private String lastName;

    /**
     * User username
     */
    @Length(min = 2, max = 15)
    @NotNull
    private String username;

    /**
     * User password
     */
    @Length(min = 8, max = 25)
    @NotNull

    private String password;

    /**
     * User password confirmation field (should be the same as password)
     */
    @Length(min = 8, max = 25)
    @NotNull
    private String confirmPassword;

    /**
     * User role
     */
    @NotNull
    private String role;


}
