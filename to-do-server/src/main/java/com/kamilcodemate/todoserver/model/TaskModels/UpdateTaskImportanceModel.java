package com.kamilcodemate.todoserver.model.TaskModels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request model for adding new task
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskImportanceModel {

    /**
     * User username
     */
    @NotNull
    @NotBlank
    private String username;


    /**
     * Importance state
     */
    @NotNull
    private boolean isImportant;


    /**
     * Id of a task to update
     */
    @NotNull
    private Long taskId;
}
