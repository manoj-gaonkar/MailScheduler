package com.project.mailscheduler.Scheduler;

import com.project.mailscheduler.model.ScheduledMail;
import com.project.mailscheduler.repository.ScheduledMailRepository;
import com.project.mailscheduler.service.EmailSenderService;
import com.project.mailscheduler.service.ScheduledMailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class MailScheduler {

    @Autowired
    private ScheduledMailRepository scheduledMailRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Scheduled(cron = "1 * * * * ?")
    public void executeScheduledMails(){
        System.out.println(LocalDateTime.now()+"working fine bud");
        List<ScheduledMail> scheduledMails = scheduledMailRepository.findByScheduleTimeBefore(LocalDateTime.now());
        System.out.println(scheduledMails.stream().count());

        for(ScheduledMail scheduledMail:scheduledMails){
            try{
                emailSenderService.sendScheduledMail(scheduledMail.getToAddress(),scheduledMail.getCc(), scheduledMail.getSubject(), scheduledMail.getBody());
            }catch (Exception ex){
                System.out.println("Failed to send email: "+ex.getMessage());
            }
        }

    }
}
