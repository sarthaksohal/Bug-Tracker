package com.project.bug_tracking_system.entity.dto;

import com.project.bug_tracking_system.entity.User;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProjectDto {

    private int id;

    private String projectName;

    private String description;

    private int createdById;

    private List<Integer> userArrayList;

}
