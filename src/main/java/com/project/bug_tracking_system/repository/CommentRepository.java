package com.project.bug_tracking_system.repository;

import com.project.bug_tracking_system.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByIssue_id(int issueId);

    List<Comment> findByUser_id(int userId);
}