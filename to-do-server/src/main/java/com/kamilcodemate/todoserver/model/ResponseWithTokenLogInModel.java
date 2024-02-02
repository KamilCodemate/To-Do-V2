package com.kamilcodemate.todoserver.model;

import com.kamilcodemate.todoserver.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWithTokenLogInModel {
    @NotNull
    private User user;
    @NotNull
    @NotBlank
    private String token;
}
