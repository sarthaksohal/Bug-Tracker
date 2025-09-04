package com.project.bug_tracking_system.entity.dto;

import com.project.bug_tracking_system.entity.type.UserRole;
import lombok.Data;

import java.util.List;
@Data
public class ProjectToUserDto {
    private List<String> projectIds;
    private String email;
    private UserRole role;
    private int assignedById;
}
