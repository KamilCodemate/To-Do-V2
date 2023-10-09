package com.kamilcodemate.todoserver.model.TaskModels;


import com.kamilcodemate.todoserver.model.AuthorizationRequestModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTaskRequestModel {

   private String token;
   private String username;
   private String name;

}
