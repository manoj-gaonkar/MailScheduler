package com.project.mailscheduler.service;

import com.project.mailscheduler.dto.ScheduledMailCreateRequestDto;
import com.project.mailscheduler.dto.ScheduledMailGetResponseDto;
import com.project.mailscheduler.dto.ScheduledMailUpdateRequestDto;
import com.project.mailscheduler.exception.ScheduleMailNotFoundException;
import com.project.mailscheduler.model.ScheduledMail;
import com.project.mailscheduler.model.User;
import com.project.mailscheduler.repository.ScheduledMailRepository;
import com.project.mailscheduler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ScheduledMailServiceImpl implements ScheduledMailService{

    @Autowired
    private ScheduledMailRepository scheduledMailRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ScheduledMailGetResponseDto getScheduledMailById(Long id) {
        Optional<ScheduledMail> mail = scheduledMailRepository.findById(id);

        if(mail.isPresent()){
            return ScheduledMailGetResponseDto.fromEntity(mail.get());
        } else {
            throw new ScheduleMailNotFoundException(id,"Scheduled Mail");
        }

    }

    public List<ScheduledMailGetResponseDto> getAllScheduledMails(){
        List<ScheduledMail> allmails = scheduledMailRepository.findAll();

        if(allmails.isEmpty()){
            return null;
        }else{
            return allmails.stream()
                    .map(ScheduledMailGetResponseDto::fromEntity)
                    .collect(Collectors.toList());
        }
    }

    public ScheduledMailGetResponseDto createSchedule(ScheduledMailCreateRequestDto newschedule){
        ScheduledMail newmail = ScheduledMailCreateRequestDto.toEntity(newschedule);
        Optional<User>  user = userRepository.findByUsername(newschedule.getUser());

        if(user.isPresent()){
            User foundUser = user.get();

            newmail.setUser(foundUser);
            newmail.setCreatedBy(foundUser.getUsername());
            scheduledMailRepository.save(newmail);

            return ScheduledMailGetResponseDto.fromEntity(newmail);

        }
        else{
            throw new RuntimeException("User not found");
        }
    }

    public String deleteScheduledMail(Long scheduleId){
        ScheduledMail mailToDelete = scheduledMailRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleMailNotFoundException(scheduleId,"Scheduled mail"));

        String mailtemplate = mailToDelete.getTemplate();
        scheduledMailRepository.delete(mailToDelete);

        return "Scheduled mail "+mailtemplate + "is deleted successfully";
    }

    public ScheduledMailGetResponseDto updateScheduledMail(Long id,ScheduledMailUpdateRequestDto updatedschedule){
        ScheduledMail existingScheduleMail = scheduledMailRepository.findById(id)
                .orElseThrow(()->new ScheduleMailNotFoundException(id,"ScheduleMail"));

        if(updatedschedule.getToAddress() != null){
            existingScheduleMail.setToAddress(updatedschedule.getToAddress());
        }
        if(updatedschedule.getCc() != null){
            existingScheduleMail.setCc(updatedschedule.getCc());
        }
        if(updatedschedule.getSubject() != null){
            existingScheduleMail.setSubject(updatedschedule.getSubject());
        }
        if(updatedschedule.getBody() != null){
            existingScheduleMail.setBody(updatedschedule.getBody());
        }
        if(updatedschedule.getTemplate() != null){
            existingScheduleMail.setTemplate(updatedschedule.getTemplate());
        }
        if(updatedschedule.getScheduleTime() != null){
            existingScheduleMail.setScheduleTime(updatedschedule.getScheduleTime());
        }


        return ScheduledMailGetResponseDto.fromEntity(scheduledMailRepository.save(existingScheduleMail));




    }
}
