package com.project.bug_tracking_system.controller;

import com.project.bug_tracking_system.entity.Issue;
import com.project.bug_tracking_system.entity.dto.DeleteIssueDto;
import com.project.bug_tracking_system.entity.dto.IssueDto;
import com.project.bug_tracking_system.entity.type.Status;
import com.project.bug_tracking_system.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issue")
@CrossOrigin(origins = "https://bug-tracker-front-end.vercel.app")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping("/save")
    public ResponseEntity<?> saveIssue(@RequestBody IssueDto issueDto) {
        return issueService.saveIssue(issueDto);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Issue>> getAllIssue() {
        return issueService.getAllIssue();
    }

    @GetMapping("/get/byId/{id}")
    public ResponseEntity<Issue> getIssueById(@PathVariable int id) {
        return ResponseEntity.ok(issueService.getIssueById(id));
    }

    @GetMapping("/get/byProject/{id}")
    public ResponseEntity<List<Issue>> getIssueByProject(@PathVariable int id) {
        return issueService.getIssueByProject(id);
    }

    @GetMapping("/get/byReporter/{id}")
    public ResponseEntity<List<Issue>> getIssueByReporter(@PathVariable int id) {
        return issueService.getIssueByReporter(id);
    }

    @PostMapping("/assign/issue/{issueId}/user/{userId}/by/{mainId}")
    public ResponseEntity<?> assignIssueToUser(@PathVariable int issueId, @PathVariable int userId, @PathVariable int mainId) {
        return issueService.assignIssueToUser(issueId, userId, mainId);
    }

    @PostMapping("update/{issueId}/status")
    public ResponseEntity<?> updateStatus(@PathVariable int issueId, @RequestParam int userId, @RequestParam Status status) {
        return issueService.updateIssueStatus(issueId, userId, status);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteIssue(@RequestBody DeleteIssueDto deleteIssueDto) {
        return issueService.deleteIssue(deleteIssueDto);
    }
}
