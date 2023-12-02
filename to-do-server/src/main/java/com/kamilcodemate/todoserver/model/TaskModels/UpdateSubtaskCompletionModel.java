package com.kamilcodemate.todoserver.model.TaskModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSubtaskCompletionModel {

    private String username;
    private Long subtaskId;
    private boolean done;
}
