package com.kamilcodemate.todoserver.service;


import com.kamilcodemate.todoserver.exception.InvalidTokenException;

public interface SubtaskService {

    public Integer updateSubtaskCompletion(String username, String token, Long subtaskId, boolean isDone)  throws InvalidTokenException;
}
