package com.project.bug_tracking_system.controller;

import com.project.bug_tracking_system.entity.Comment;
import com.project.bug_tracking_system.entity.dto.CommentDto;
import com.project.bug_tracking_system.service.CommentService;
import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "https://bug-tracker-front-2puktb28l-sarthaks-projects-8c3ad2a1.vercel.app")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add/{issueId}/user/{userId}")
    public ResponseEntity<?> saveComment(@PathVariable int issueId, @RequestBody CommentDto commentDto,@PathVariable int userId) {
        return commentService.save(issueId, commentDto,userId);
    }

    @GetMapping("/getAll/issue/{issueId}")
    public ResponseEntity<List<Comment>> getAllCommentForIssueId(@PathVariable int issueId) {
        return commentService.getCommentForIssueId(issueId);
    }

    @GetMapping("/getAll/user/{userId}")
    public ResponseEntity<List<Comment>> getAllCommentForUserId(@PathVariable int userId) {
        return ResponseEntity.ok(commentService.getCommentForUserId(userId));
    }

    @DeleteMapping("/{commentId}/byUser/{userId}")
    public ResponseEntity<?> deleteComment(@PathVariable int commentId, @PathVariable int userId) {
        return commentService.deleteComment(commentId, userId);
    }

}
