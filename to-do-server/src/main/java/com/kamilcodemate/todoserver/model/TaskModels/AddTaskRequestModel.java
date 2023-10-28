package com.kamilcodemate.todoserver.model.TaskModels;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTaskRequestModel {

   private String username;
   private String name;

}
