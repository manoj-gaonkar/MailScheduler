package com.project.mailscheduler.Scheduler;

import com.project.mailscheduler.model.ScheduledMail;
import com.project.mailscheduler.repository.ScheduledMailRepository;
import com.project.mailscheduler.service.EmailSenderService;
import com.project.mailscheduler.service.ScheduledMailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class MailScheduler {

    @Autowired
    private ScheduledMailRepository scheduledMailRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Scheduled(cron = "1 * * * * ?")
    @Transactional
    public void executeScheduledMails(){
        List<ScheduledMail> scheduledMails = scheduledMailRepository.findByScheduleTimeBefore(LocalDateTime.now());

        for(ScheduledMail scheduledMail:scheduledMails){
            //checking if the mail is sent already and if it is active
            System.out.println("i am outside if");
            if(!scheduledMail.getIsSent() && scheduledMail.getIsActive()) {
                System.out.println("i am inside if");
                try {
                    emailSenderService.sendScheduledMail(scheduledMail.getToAddress(), scheduledMail.getCc(), scheduledMail.getSubject(), scheduledMail.getBody());
                } catch (Exception ex) {
                    System.out.println("Failed to send email: " + ex.getMessage());
                }
            }
        }

    }
}
