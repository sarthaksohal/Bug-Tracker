package com.project.bug_tracking_system.repository;

import com.project.bug_tracking_system.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Integer> {
   List<Issue> findByProject_Id(int id);

    List<Issue> findByReporter_id(int id);

}