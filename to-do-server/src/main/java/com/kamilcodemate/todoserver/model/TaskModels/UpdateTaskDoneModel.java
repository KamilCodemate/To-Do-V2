package com.kamilcodemate.todoserver.model.TaskModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request model for update task execution state
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskDoneModel {

    /**
     * Name of the user to whom the task belongs
     */
    private String username;
    /**
     * Determine whether the task is complete
     */
    private boolean done;
    /**
     * Id of task to update
     */
    private Long taskId;
}
