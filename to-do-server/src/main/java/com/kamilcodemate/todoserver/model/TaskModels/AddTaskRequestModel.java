package com.kamilcodemate.todoserver.model.TaskModels;



import com.kamilcodemate.todoserver.entity.Subtask;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
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
   private String username;


   /**
    * Task name
    */
   @NotNull
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


   private String description;
   /**
    * List of subtasks
    */
   private List<Subtask> subtasks;

}
