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


/**
 * Request model for adding new task
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddTaskRequestModel {

   /**
    * User username
    */
   @NotNull
   @NotBlank
   private String username;


   /**
    * Task name
    */
   @NotNull(message = "Task name cannot be null")
   @NotBlank(message = "Task name cannot be blank")
   @Length(min = 1, max = 20, message = "Task name must contain between 1 and 20 characters")
   private String name;
   /**
    * Task deadline date (without time)
    */
   private LocalDate date;
   /**
    * Time when Task is going to start
    */
   private Time startTime;

   /**
    * Time when Task is going to end
    */
   private Time endTime;
   
   /**
    * Task description
    */
   private String description;
   /**
    * List of subtasks
    */
   private List<Subtask> subtasks;

}
