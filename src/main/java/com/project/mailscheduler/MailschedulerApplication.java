package com.project.mailscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MailschedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailschedulerApplication.class, args);
	}

}
