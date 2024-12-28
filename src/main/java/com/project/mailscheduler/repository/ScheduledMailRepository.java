package com.project.mailscheduler.repository;

import com.project.mailscheduler.model.ScheduledMail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduledMailRepository extends JpaRepository<ScheduledMail,Long> {
    List<ScheduledMail> findByScheduleTimeBefore(LocalDateTime now);
}
