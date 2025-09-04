package com.project.bug_tracking_system.repository;

import com.project.bug_tracking_system.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}