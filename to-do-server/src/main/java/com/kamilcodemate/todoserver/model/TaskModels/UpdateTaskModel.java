package com.kamilcodemate.todoserver.model.TaskModels;

import com.kamilcodemate.todoserver.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskModel {

    private String username;
    private boolean isImportant;
    private Long taskId;
}
