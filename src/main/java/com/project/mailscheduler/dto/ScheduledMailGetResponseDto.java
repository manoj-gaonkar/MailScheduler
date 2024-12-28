package com.project.mailscheduler.dto;

import com.project.mailscheduler.model.ScheduledMail;
import com.project.mailscheduler.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ScheduledMailGetResponseDto {

    private Long id;
    private String subject;
    private List<String> toAddress;
    private List<String> cc;
    private String body;
    private String template;
    private Date scheduleTime;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String username;

    public static ScheduledMailGetResponseDto fromEntity(ScheduledMail scheduledMail){
        ScheduledMailGetResponseDto dto = new ScheduledMailGetResponseDto();
        dto.setCc(scheduledMail.getCc());
        dto.setId(scheduledMail.getId());
        dto.setBody(scheduledMail.getBody());
        dto.setSubject(scheduledMail.getSubject());
        dto.setScheduleTime(scheduledMail.getScheduleTime());
        dto.setTemplate(scheduledMail.getTemplate());
        dto.setToAddress(scheduledMail.getToAddress());
        dto.setUpdatedAt(scheduledMail.getUpdatedAt());
        dto.setCreatedBy(scheduledMail.getCreatedBy());
//        dto.setUsers(scheduledMail.getUsers());
        dto.setCreatedAt(scheduledMail.getCreatedAt());

        return dto;
    }

}
