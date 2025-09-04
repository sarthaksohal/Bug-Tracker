package com.project.bug_tracking_system.service;

import com.project.bug_tracking_system.entity.Project;
import com.project.bug_tracking_system.entity.User;
import com.project.bug_tracking_system.entity.dto.ProjectDto;
import com.project.bug_tracking_system.entity.dto.ProjectToUserDto;
import com.project.bug_tracking_system.entity.dto.UserDto;
import com.project.bug_tracking_system.entity.type.UserRole;
import com.project.bug_tracking_system.repository.ProjectRepository;
import com.project.bug_tracking_system.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j

public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;


    public ResponseEntity<?> saveTheProject(ProjectDto projectDto) {
        try {
            List<User> users = new ArrayList<>();
            User createdByUser = userRepository.findById(projectDto.getCreatedById()).get();
            if (createdByUser.getUserRole().equals(UserRole.ADMIN)) {
                for (int id : projectDto.getUserArrayList()) {
                    User user = userRepository.findById(id).get();
                    users.add(user);
                }
                Project project = Project.builder().
                        projectName(projectDto.getProjectName())
                        .createdBy(createdByUser)
                        .description(projectDto.getDescription())
                        .userArrayList(users)
                        .build();
                projectRepository.save(project);

                return new ResponseEntity<>("saved " + project, HttpStatus.OK);
            } else {
                log.info("Your role {} is not specified to create a project", createdByUser.getUserRole());
                return new ResponseEntity<>("role not specified", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.info("Error happened while parsing the data {}", e);
            return new ResponseEntity<>(e, HttpStatus.BAD_GATEWAY);
        }
    }

    public Project getProjectById(int id) {
        return projectRepository.findById(id).get();
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public String assignProjectToUser(ProjectToUserDto projectToUserDto) {
        User addingUser = userRepository.findById(projectToUserDto.getAssignedById()).get();
        if (addingUser.getUserRole().equals(UserRole.ADMIN) || addingUser.getUserRole().equals(UserRole.MANAGER)) {
            List<String> projectIds = projectToUserDto.getProjectIds();
            User user1 = userRepository.findByEmail(projectToUserDto.getEmail());
            user1.setUserRole(projectToUserDto.getRole());
            List<Project> projectList = user1.getProjectList();
            for (String projectId : projectIds) {
                Project project = projectRepository.findById(Integer.valueOf(projectId)).get();
                if (!projectList.contains(project)) {
                    project.getUserArrayList().add(user1);
                    user1.getProjectList().add(project);
                    projectRepository.save(project);
                    log.info("User added Successfully in the project ", project.getProjectName());

                } else {
                    log.info("It has already been saved !!");
                }

            }
            userRepository.save(user1);

            return "saved";
        } else {
            log.info("You are not eligible to add in the project as your role is {}", addingUser.getUserRole());
            return "Fail to add as you role is not correct to add";
        }
    }

    public List<User> getUsersById(int id) {
        List<User> users = projectRepository.findById(id).get().getUserArrayList();
        return users;
    }

    public ResponseEntity<List<String>> getAllProjectName() {
        List<Project> projects = projectRepository.findAll();
        List<String> projectName = new ArrayList<>();
        for (Project project : projects) {
            projectName.add(project.getProjectName());
        }
        return ResponseEntity.ok(projectName);
    }
}

