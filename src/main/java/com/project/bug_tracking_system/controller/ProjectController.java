package com.project.bug_tracking_system.controller;

import com.project.bug_tracking_system.entity.Project;
import com.project.bug_tracking_system.entity.User;
import com.project.bug_tracking_system.entity.dto.ProjectDto;
import com.project.bug_tracking_system.entity.dto.ProjectToUserDto;
import com.project.bug_tracking_system.entity.dto.UserDto;
import com.project.bug_tracking_system.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "https://bug-tracker-front-2puktb28l-sarthaks-projects-8c3ad2a1.vercel.app")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/create")
    private ResponseEntity<?> createProject(@RequestBody ProjectDto projectDto) {
        return ResponseEntity.ok(projectService.saveTheProject(projectDto));
    }

    @PostMapping("/assign")
    private ResponseEntity<String> addUserToProject(@RequestBody ProjectToUserDto projectToUserDto){
        return ResponseEntity.ok(projectService.assignProjectToUser(projectToUserDto));
    }

    @GetMapping("/get/{id}")
    private ResponseEntity<Project> getProjectById(@PathVariable int id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @GetMapping("/get")
    private ResponseEntity<List<Project>> getAllProject() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }
    @GetMapping("/getUsers/{id}")
    private ResponseEntity<List<User>> getUsersForProject(@PathVariable int id) {
        return ResponseEntity.ok(projectService.getUsersById(id));
    }
}
