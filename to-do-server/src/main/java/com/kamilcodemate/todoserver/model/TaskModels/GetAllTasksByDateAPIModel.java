package com.kamilcodemate.todoserver.model.TaskModels;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTasksByDateAPIModel {

    String username;
    LocalDate date;
}
