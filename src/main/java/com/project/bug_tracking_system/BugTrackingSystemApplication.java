package com.project.bug_tracking_system;

import com.project.bug_tracking_system.entity.Comment;
import com.project.bug_tracking_system.entity.Issue;
import com.project.bug_tracking_system.entity.Project;
import com.project.bug_tracking_system.entity.User;
import com.project.bug_tracking_system.entity.type.Severity;
import com.project.bug_tracking_system.entity.type.Status;
import com.project.bug_tracking_system.entity.type.UserRole;
import com.project.bug_tracking_system.repository.CommentRepository;
import com.project.bug_tracking_system.repository.IssueRepository;
import com.project.bug_tracking_system.repository.ProjectRepository;
import com.project.bug_tracking_system.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BugTrackingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BugTrackingSystemApplication.class, args);
	}
}

