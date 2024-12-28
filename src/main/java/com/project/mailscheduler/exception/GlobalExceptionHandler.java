package com.project.mailscheduler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ScheduleMailNotFoundException.class)
    public ResponseEntity<Object> SchduleMailExceptionHandler(ScheduleMailNotFoundException ex){
        HashMap<String, Object> map = new HashMap<>();
        map.put("error",ex.getMessage());
        map.put("timestamp", LocalDateTime.now());
        map.put("errorDetails", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
    }

}
