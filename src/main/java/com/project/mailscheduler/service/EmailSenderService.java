package com.project.mailscheduler.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendScheduledMail(List<String> toAddresses, List<String> ccs, String subject, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        SimpleMailMessage message = new SimpleMailMessage();
        helper.setFrom("manojgaonkar7648@gmail.com");
        helper.setTo(toAddresses.toArray(new String[0]));
        helper.setCc(ccs.toArray(new String[0]));
        helper.setSubject(subject);
        helper.setText(body,true);

        System.out.println(message);
        mailSender.send(message);
        System.out.println("mail sent");
    }
}
