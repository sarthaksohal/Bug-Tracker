package com.project.bug_tracking_system.entity.dto;


import com.project.bug_tracking_system.entity.Project;
import com.project.bug_tracking_system.entity.User;
import com.project.bug_tracking_system.entity.type.Severity;
import com.project.bug_tracking_system.entity.type.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IssueDto {

    private int id;

    private String title;

    private String description;

    private Severity severity;

    private Status status;

    private int reporterId;

    private int projectId;


}
