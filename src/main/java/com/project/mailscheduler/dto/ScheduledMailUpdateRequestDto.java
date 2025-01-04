package com.project.mailscheduler.dto;

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

@Setter
@Getter
@Schema(name = "Update Request payload")
public class ScheduledMailUpdateRequestDto {

    @NotEmpty(message = "subject should not be empty")
    private String subject;
    @NotEmpty(message = "to address should not be empty")
    private List<@Email(message = "the to address email should be valid") String> toAddress;
    private List<@Email(message = "the cc address email should be valid") String> cc;

    @NotEmpty(message = "body should not be empty")
    private String body;

    @NotEmpty(message = "template should not be empty")
    private String template;

    @Future
    @NotNull(message = "schedule time should not be null")
    private Date scheduleTime;
    private Boolean isActive;
}
