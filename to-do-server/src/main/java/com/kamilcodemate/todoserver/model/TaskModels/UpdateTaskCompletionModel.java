package com.kamilcodemate.todoserver.model.TaskModels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request model for update task execution state
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskCompletionModel {

    /**
     * Name of the user to whom the task belongs
     */
    @NotNull
    @NotBlank
    private String username;
    /**
     * Determine whether the task is complete
     */
    @NotNull
    private boolean isDone;
    /**
     * Id of task to update
     */
    @NotNull
    private Long taskId;
}
