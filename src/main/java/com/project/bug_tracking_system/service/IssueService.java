package com.project.bug_tracking_system.service;

import com.project.bug_tracking_system.controller.IssueController;
import com.project.bug_tracking_system.entity.Issue;
import com.project.bug_tracking_system.entity.Project;
import com.project.bug_tracking_system.entity.User;
import com.project.bug_tracking_system.entity.dto.DeleteIssueDto;
import com.project.bug_tracking_system.entity.dto.IssueDto;
import com.project.bug_tracking_system.entity.type.Status;
import com.project.bug_tracking_system.entity.type.UserRole;
import com.project.bug_tracking_system.repository.IssueRepository;
import com.project.bug_tracking_system.repository.ProjectRepository;
import com.project.bug_tracking_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class IssueService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;


    public ResponseEntity<?> saveIssue(IssueDto issueDto) {
        int projectId = issueDto.getProjectId();
        int reporterId = issueDto.getReporterId();
        Project project = projectService.getProjectById(projectId);
        User user = userService.getUserById(reporterId);
        if (project.getUserArrayList().contains(user)) {
            Issue issue = Issue.builder()
                    .title(issueDto.getTitle())
                    .description((issueDto.getDescription()))
                    .reporter(user)
                    .status(issueDto.getStatus())
                    .severity(issueDto.getSeverity())
                    .project(project)
                    .build();
            issueRepository.save(issue);
            return ResponseEntity.ok("saved" + issue);
        } else {
            log.info("You are not eligible to add in this project as not part of project");
            return new ResponseEntity("Not eligible to add", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Issue>> getAllIssue() {
        return ResponseEntity.ok(issueRepository.findAll());
    }

    public Issue getIssueById(int id) {
        return issueRepository.findById(id).get();

    }

    public ResponseEntity<List<Issue>> getIssueByProject(int id) {
        List<Issue> issues = issueRepository.findByProject_Id(id);
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    public ResponseEntity<List<Issue>> getIssueByReporter(int id) {
        List<Issue> issues = issueRepository.findByReporter_id(id);
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> assignIssueToUser(int issueId, int userId, int mainId) {
        User mainUser = userRepository.findById(mainId).get();
        if (mainUser.getUserRole().equals(UserRole.ADMIN) || mainUser.getUserRole().equals(UserRole.MANAGER)) {
            Issue issue = issueRepository.findById(issueId).get();
            User userToBeAssigned = userRepository.findById(userId).get();
            if (issue.getProject().getUserArrayList().contains(userToBeAssigned)) {
                issue.setAssignee(userToBeAssigned);
                return new ResponseEntity<>("assigned to user " + userToBeAssigned.getName() + " successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("You are not part of this project " +
                        "so how can you handle this issue for this project", HttpStatus.BAD_REQUEST);
            }

        } else {
            return new ResponseEntity<>("You are not eligible to assign user to issue", HttpStatus.BAD_REQUEST);
        }

    }

    @Transactional
    public ResponseEntity<?> updateIssueStatus(int issueId, int userId, Status newStatus) {
        Issue issue = issueRepository.findById(issueId).get();
        User user = userRepository.findById(userId).get();

        if (issue.getAssignee() == null || issue.getAssignee().getId() != user.getId()) {
            return new ResponseEntity<>("Only the assignee can change the status", HttpStatus.FORBIDDEN);
        }
        issue.setStatus(newStatus);
        issueRepository.save(issue);
        return new ResponseEntity<>("Issue status updated to " + newStatus, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteIssue(int issueId,int userId) {
        Issue issue = issueRepository.findById(issueId).get();
        Status status = issue.getStatus();
        User user = userRepository.findById(userId).get();
        if (user.getUserRole().equals(UserRole.ADMIN) && status.equals(Status.CLOSED)) {
            issueRepository.delete(issue);
            return new ResponseEntity<>("deleted issue successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("you cant delete either you are not admin or status is not closed", HttpStatus.BAD_REQUEST);
        }
    }
}
