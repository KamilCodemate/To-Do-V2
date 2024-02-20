package com.kamilcodemate.todoserver.model.TaskModels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInRequestModel {
    @NotNull(message = "Please provide username")
    @NotBlank(message = "Please provide username")
    private String username;

    @NotNull(message = "Please provide password")
    @NotBlank(message = "Please provide password")
    private String password;
}
