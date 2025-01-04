package com.project.mailscheduler.exception;

import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private UnexpectedTypeException ex;

    @ExceptionHandler(ScheduleMailNotFoundException.class)
    public ResponseEntity<Object> SchduleMailExceptionHandler(ScheduleMailNotFoundException ex){
        HashMap<String, Object> map = new HashMap<>();
        map.put("error",ex.getMessage());
        map.put("timestamp", LocalDateTime.now());
        map.put("errorDetails", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String , String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> errors.put(error.getObjectName(), error.getDefaultMessage()));

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);

    }

//    @ExceptionHandler(UnexpectedTypeException.class)
//    public ResponseEntity<Object> handleTypeValidationExceptions(UnexpectedTypeException ex){
//        String errorMessage = ex.getLocalizedMessage();
//        return  new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST)}

}
