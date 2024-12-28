package com.project.mailscheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendScheduledMail(List<String> toAddresses, List<String> ccs, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toAddresses.toArray(new String[0]));
        message.setCc(ccs.toArray(new String[0]));
        message.setSubject(subject);
        message.setText(body);

        System.out.println(message);
        mailSender.send(message);
    }
}
