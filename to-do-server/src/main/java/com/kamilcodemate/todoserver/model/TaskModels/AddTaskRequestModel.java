package com.kamilcodemate.todoserver.model.TaskModels;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
   private String username;


   /**
    * Task name
    */
   private String name;

}
