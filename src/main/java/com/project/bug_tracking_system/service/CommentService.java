package com.project.bug_tracking_system.service;

import com.project.bug_tracking_system.entity.Comment;
import com.project.bug_tracking_system.entity.Issue;
import com.project.bug_tracking_system.entity.User;
import com.project.bug_tracking_system.entity.dto.CommentDto;
import com.project.bug_tracking_system.entity.type.UserRole;
import com.project.bug_tracking_system.repository.CommentRepository;
import com.project.bug_tracking_system.repository.IssueRepository;
import com.project.bug_tracking_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;


    public ResponseEntity<?> save(int issueId, CommentDto commentDto,int userId) {
        Issue issue = issueRepository.findById(issueId).get();
        User user = userRepository.findById(userId).get();

        if (issue.getProject().getUserArrayList().contains(user)) {
            Comment comment = Comment.builder()
                    .user(user)
                    .text(commentDto.getText())
                    .issue(issue)
                    .build();
            commentRepository.save(comment);
            return ResponseEntity.ok("saved successfully");
        } else {
            return new ResponseEntity<>("you are not eligible to add comment", HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<List<Comment>> getCommentForIssueId(int issueId) {
        List<Comment> comments = commentRepository.findByIssue_id(issueId);
        return ResponseEntity.ok(comments);
    }

    public List<Comment> getCommentForUserId(int userId) {
        return commentRepository.findByUser_id(userId);
    }

    public ResponseEntity<?> deleteComment(int commentId,int userId) {
        Comment comment = commentRepository.findById(commentId).get();
        User user = userRepository.findById(userId).get();

        if (comment.getIssue().getProject().getUserArrayList().contains(user)&&user.getUserRole().equals(UserRole.ADMIN)) {
            commentRepository.deleteById(commentId);
            return new ResponseEntity<>("deleted succesfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("you are not able to delete",HttpStatus.FORBIDDEN);
        }
    }
}
