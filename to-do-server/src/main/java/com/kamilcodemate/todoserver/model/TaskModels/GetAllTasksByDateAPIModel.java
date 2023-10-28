package com.kamilcodemate.todoserver.model.TaskModels;


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
    String username;

    /**
     * Day which tasks must be from
     */
    LocalDate date;
}
