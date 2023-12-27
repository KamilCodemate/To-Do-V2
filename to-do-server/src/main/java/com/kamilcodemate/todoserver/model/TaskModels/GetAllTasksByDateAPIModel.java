package com.kamilcodemate.todoserver.model.TaskModels;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Request model for getting tasks for particular day
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTasksByDateAPIModel {

    /**
     * User username
     */
    @NotNull
    @NotBlank
    String username;

    /**
     * Day which tasks must be from
     */
    @NotNull
    @NotBlank
    LocalDate date;
}
