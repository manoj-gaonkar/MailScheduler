package com.project.mailscheduler.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username",nullable = true)
    private String username;

    @Column(name="firstname",nullable = true)
    private String firstname;

    @Column(name="lastname",nullable = true)
    private String lastname;

    @Column(name="email",nullable = true)
    private String Email;

    @CreatedDate
    @Column(name="created_at",nullable = false,updatable = false)
    private Date createdAt;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ScheduledMail> scheduledMails = new ArrayList<>();


}
