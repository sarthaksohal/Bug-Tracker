package com.project.bug_tracking_system.entity.dto;

import lombok.Data;

@Data
public class DeleteIssueDto {
    private int issueId;
    private int userId;
}
