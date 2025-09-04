package com.project.bug_tracking_system.entity.dto;

import lombok.Data;

@Data
public class LoginBody {
    private String email;
    private String password;
}
