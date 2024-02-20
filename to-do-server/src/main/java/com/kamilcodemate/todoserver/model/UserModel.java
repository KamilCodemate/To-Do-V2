package com.kamilcodemate.todoserver.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
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
    @NotNull(message = "First name cannot be null")
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    /**
     * User last name
     */
    @Length(min = 2, max = 40, message = "Last name must be between 2 and 40 characters")
    @NotNull(message = "Last name cannot be null")
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    /**
     * User username
     */
    @Length(min = 2, max = 15)
    @NotNull(message = "Username cannot be null")
    @NotBlank(message = "Username cannot be blank")
    private String username;

    /**
     * User password
     */
    @Length(min = 8, max = 25, message = "Password must be between 8 and 25 characters")
    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    private String password;

    /**
     * User password confirmation field (should be the same as password)
     */

    private String confirmPassword;

    /**
     * User role
     */
    @NotNull
    private String role;


}
