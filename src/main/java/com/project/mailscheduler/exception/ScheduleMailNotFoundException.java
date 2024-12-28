package com.project.mailscheduler.exception;


public class ScheduleMailNotFoundException extends RuntimeException{

    private final Long id;
    private final String entityName;
    public ScheduleMailNotFoundException(Long id, String entityName){
        super(String.format("%s with id %id not found",entityName,id));
        this.entityName = entityName;
        this.id = id;
    }

    private Long getId(){
        return id;
    }

    private String getEntityName(){
        return entityName;
    }

}
