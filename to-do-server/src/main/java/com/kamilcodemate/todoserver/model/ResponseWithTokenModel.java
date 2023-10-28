package com.kamilcodemate.todoserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * ResponseEntity generic parameter to send Bearer Token for user
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseWithTokenModel {

    /**
     * Response message
     */
    private String message;

    /**
     * Response Bearer Token
     */
    private String token;
}
