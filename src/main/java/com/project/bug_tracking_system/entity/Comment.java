package com.project.bug_tracking_system.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="issue_id",nullable = false)
    private Issue issue;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    private String text;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
