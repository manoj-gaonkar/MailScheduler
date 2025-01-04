package com.project.mailscheduler.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
//this annotation is used to hide this schema/dto from swagger ui
@Hidden
public class ScheduledMailUpdateRequestDto {
    private String subject;
    private List<String> toAddress;
    private List<String> cc;
    private String body;
    private String template;
    private Date scheduleTime;
    private Boolean isActive;
}
