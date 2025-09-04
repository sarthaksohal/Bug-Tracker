package com.project.bug_tracking_system.entity.dto;

import com.project.bug_tracking_system.entity.type.UserRole;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private int id;        // Optional, mainly for update/get
    private String name;
    private String email;
    private String password;   // You can remove this in responses
    private UserRole userRole;
}

