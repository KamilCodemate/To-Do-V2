package com.kamilcodemate.todoserver.model.TaskModels;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.sql.Time;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditTaskRequestModel {

    @NotNull
    private Long taskId;
    @NotNull
    @Length(min = 1, max = 100)
    private String name;
    @NotBlank
    private String username;
    @NotNull
    private LocalDate date;
    @NotNull
    private Time startTime;
    @NotNull
    private Time endTime;
    @NotNull
    private boolean isDone;
    @NotNull
    private boolean isImportant;
    @NotNull
    @NotBlank
    private String description;

}
