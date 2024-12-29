package com.project.mailscheduler.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "scheduled_job")
public class ScheduledMail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "to_address")
    private List<String> toAddress;

    @Column(name = "cc")
    private List<String> cc;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "body",columnDefinition = "TEXT")
    private String body;

    @Column(name = "template")
    private String template;

    @Column(name = "scheduleTime")
    private Date scheduleTime;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_sent")
    private Boolean isSent;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

    }