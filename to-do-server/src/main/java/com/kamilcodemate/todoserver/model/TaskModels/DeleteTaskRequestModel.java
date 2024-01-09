package com.kamilcodemate.todoserver.model.TaskModels;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteTaskRequestModel {

    @NotNull
    private Long taskId;

    @NotNull
    @NotBlank
    private String username;
}
