package com.kamilcodemate.todoserver.model.TaskModels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSubtaskCompletionModel {

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    private Long subtaskId;

    @NotNull
    private boolean done;
}
