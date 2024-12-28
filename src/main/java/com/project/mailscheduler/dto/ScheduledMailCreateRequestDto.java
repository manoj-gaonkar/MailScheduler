package com.project.mailscheduler.dto;

import com.project.mailscheduler.model.ScheduledMail;
import com.project.mailscheduler.model.User;
import com.project.mailscheduler.repository.ScheduledMailRepository;
import com.project.mailscheduler.repository.UserRepository;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ScheduledMailCreateRequestDto {


    private String subject;
    private List<String> toAddress;
    private List<String> cc;
    private String body;
    private String template;
    private Date ScheduleTime;
//    private Date createdAt;
//    private Date updatedAt;
    private String createdBy;
    private String user;

    public static ScheduledMail toEntity(ScheduledMailCreateRequestDto requestDto){
        ScheduledMail scheduledMail = new ScheduledMail();
        scheduledMail.setCc(requestDto.getCc());
        scheduledMail.setToAddress(requestDto.getToAddress());
        scheduledMail.setTemplate(requestDto.getTemplate());
        scheduledMail.setScheduleTime(requestDto.getScheduleTime());
        scheduledMail.setSubject(requestDto.getSubject());
        scheduledMail.setBody(requestDto.getBody());

        return scheduledMail;
    }

}
