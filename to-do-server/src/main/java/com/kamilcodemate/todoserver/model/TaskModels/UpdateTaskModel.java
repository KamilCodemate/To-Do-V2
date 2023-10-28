package com.kamilcodemate.todoserver.model.TaskModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request model for adding new task
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskModel {

    /**
     * User username
     */
    private String username;


    /**
     * Importance state
     */
    private boolean isImportant;


    /**
     * Id of a task to update
     */
    private Long taskId;
}
