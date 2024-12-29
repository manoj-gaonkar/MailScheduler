package com.project.mailscheduler.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class ScheduledMailUpdateRequestDto {
    private String subject;
    private List<String> toAddress;
    private List<String> cc;
    private String body;
    private String template;
    private Date scheduleTime;
    private Boolean isActive;
}
