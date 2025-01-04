package com.project.mailscheduler.dto;

import com.project.mailscheduler.model.ScheduledMail;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
//this annotation is used to hide this schema/dto from swagger ui
@Schema(name = "Mail create request")
public class ScheduledMailCreateRequestDto {


    @NotEmpty(message = "subject should not be empty")
    private String subject;
    @NotEmpty(message = "to address should not be empty")
    @NotEmpty(message = "toaddress must not be empty")
    private List<@Email(message = "Email address not valid")String> toAddress;
    private List<@Email(message = "Email address not valid")String> cc;
    @NotEmpty(message = "body should not be empty")
    private String body;
    @NotEmpty(message = "template name should not be empty")
    private String template;
    @NotNull(message = "Schedule time must not be empty")
    @Future
    private Date ScheduleTime;
//    private Date createdAt;
//    private Date updatedAt;
    @Schema(hidden = true)
    private String createdBy;
    @NotEmpty(message = "Username should not be empty")
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
