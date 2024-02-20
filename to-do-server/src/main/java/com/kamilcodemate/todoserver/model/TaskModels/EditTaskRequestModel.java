package com.kamilcodemate.todoserver.model.TaskModels;
import com.kamilcodemate.todoserver.entity.Subtask;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditTaskRequestModel {

    @NotNull
    private Long taskId;
    @NotNull(message = "Task name cannot be null")
    @NotBlank(message = "Task name cannot be blank")
    @Length(min = 1, max = 20, message = "Task name must contain between 1 and 20 characters")
    private String name;
    @NotBlank
    private String username;
    @NotNull(message = "Date cannot be null")
    @NotBlank(message = "Date cannot be blank")
    private LocalDate date;
    @NotNull(message = "Start time cannot be null")
    @NotBlank(message = "Start time cannot be blank")
    private Time startTime;
    @NotNull(message = "End time cannot be null")
    @NotBlank(message = "End time cannot be blank")
    private Time endTime;
    @NotNull(message = "Completion cannot be null")
    private boolean isDone;
    @NotNull(message = "Importance cannot be null")
    private boolean isImportant;
    private List<Subtask> subtasks;
    private String description;

}
