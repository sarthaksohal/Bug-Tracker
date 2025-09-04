package com.project.bug_tracking_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.bug_tracking_system.entity.type.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @CurrentTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @JsonIgnore
    @ManyToMany(mappedBy = "userArrayList")
    private List<Project> projectList=new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "reporter")
    private List<Issue> issueList=new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Comment> commentList=new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "createdBy")
    private List<Project> projects=new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "assignee")
    private List<Issue> issues=new ArrayList<>();

}
