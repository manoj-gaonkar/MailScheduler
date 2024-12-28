package com.project.mailscheduler.service;

import com.project.mailscheduler.dto.ScheduledMailCreateRequestDto;
import com.project.mailscheduler.dto.ScheduledMailGetRequestDto;
import com.project.mailscheduler.dto.ScheduledMailGetResponseDto;
import com.project.mailscheduler.dto.ScheduledMailUpdateRequestDto;
import com.project.mailscheduler.model.ScheduledMail;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ScheduledMailService {
    ScheduledMailGetResponseDto getScheduledMailById(Long id);
    List<ScheduledMailGetResponseDto> getAllScheduledMails();

    ScheduledMailGetResponseDto createSchedule(ScheduledMailCreateRequestDto requestDto);

    ScheduledMailGetResponseDto updateScheduledMail(Long id,ScheduledMailUpdateRequestDto requestDto);

    String deleteScheduledMail(Long id);
}
