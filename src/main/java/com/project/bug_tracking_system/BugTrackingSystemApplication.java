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

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BugTrackingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BugTrackingSystemApplication.class, args);
	}
/*	@Bean
	CommandLineRunner initData(UserRepository userRepository,
							   ProjectRepository projectRepository,
							   IssueRepository issueRepository,
							   CommentRepository commentRepository) {
		return args -> {
			// -------------------- USERS --------------------
			User u1 = User.builder().name("Alice").email("alice@example.com").password("pass").userRole(UserRole.ADMIN).build();
			User u2 = User.builder().name("Bob").email("bob@example.com").password("pass").userRole(UserRole.DEVELOPER).build();
			User u3 = User.builder().name("Charlie").email("charlie@example.com").password("pass").userRole(UserRole.TESTER).build();
			User u4 = User.builder().name("David").email("david@example.com").password("pass").userRole(UserRole.DEVELOPER).build();
			User u5 = User.builder().name("Eve").email("eve@example.com").password("pass").userRole(UserRole.TESTER).build();
			User u6 = User.builder().name("Frank").email("frank@example.com").password("pass").userRole(UserRole.ADMIN).build();
			User u7 = User.builder().name("Grace").email("grace@example.com").password("pass").userRole(UserRole.DEVELOPER).build();
			User u8 = User.builder().name("Hank").email("hank@example.com").password("pass").userRole(UserRole.TESTER).build();
			User u9 = User.builder().name("Ivy").email("ivy@example.com").password("pass").userRole(UserRole.DEVELOPER).build();
			User u10 = User.builder().name("Jack").email("jack@example.com").password("pass").userRole(UserRole.TESTER).build();

			List<User> users = userRepository.saveAll(List.of(u1,u2,u3,u4,u5,u6,u7,u8,u9,u10));

			// -------------------- PROJECTS --------------------
			Project p1 = Project.builder().projectName("BugTracker Core").description("Core system for bug tracking").createdBy(u1).userArrayList(new ArrayList<>(List.of(u1,u2,u3))).build();
			Project p2 = Project.builder().projectName("UI Module").description("Frontend UI in React").createdBy(u2).userArrayList(new ArrayList<>(List.of(u2,u4,u5))).build();
			Project p3 = Project.builder().projectName("API Gateway").description("Spring Cloud Gateway for microservices").createdBy(u3).userArrayList(new ArrayList<>(List.of(u3,u6,u7))).build();
			Project p4 = Project.builder().projectName("Auth Service").description("Authentication and Authorization module").createdBy(u4).userArrayList(new ArrayList<>(List.of(u4,u8,u9))).build();
			Project p5 = Project.builder().projectName("Notification Service").description("Email/SMS notifications").createdBy(u5).userArrayList(new ArrayList<>(List.of(u5,u10,u1))).build();
			Project p6 = Project.builder().projectName("Analytics Dashboard").description("Bug analytics charts").createdBy(u6).userArrayList(new ArrayList<>(List.of(u6,u2,u7))).build();
			Project p7 = Project.builder().projectName("Mobile App").description("Android/iOS client").createdBy(u7).userArrayList(new ArrayList<>(List.of(u7,u3,u8))).build();
			Project p8 = Project.builder().projectName("Payment Service").description("Integrates payment systems").createdBy(u8).userArrayList(new ArrayList<>(List.of(u8,u9,u4))).build();
			Project p9 = Project.builder().projectName("Search Service").description("ElasticSearch based search engine").createdBy(u9).userArrayList(new ArrayList<>(List.of(u9,u10,u5))).build();
			Project p10 = Project.builder().projectName("Report Generator").description("Generates PDF/Excel reports").createdBy(u10).userArrayList(new ArrayList<>(List.of(u10,u1,u6))).build();

			List<Project> projects = projectRepository.saveAll(List.of(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10));

			// -------------------- ISSUES --------------------
			Issue i1 = Issue.builder().title("Login button not working").description("Login button is unresponsive").severity(Severity.HIGH).status(Status.OPEN).reporter(u3).assignee(u2).project(p1).build();
			Issue i2 = Issue.builder().title("UI alignment bug").description("Header misaligned in Firefox").severity(Severity.MEDIUM).status(Status.OPEN).reporter(u5).assignee(u4).project(p2).build();
			Issue i3 = Issue.builder().title("Gateway timeout").description("Requests timing out after 30s").severity(Severity.CRITICAL).status(Status.IN_PROGRESS).reporter(u7).assignee(u6).project(p3).build();
			Issue i4 = Issue.builder().title("JWT not validated").description("Expired tokens still accepted").severity(Severity.CRITICAL).status(Status.OPEN).reporter(u8).assignee(u4).project(p4).build();
			Issue i5 = Issue.builder().title("Emails not sent").description("SMTP server config missing").severity(Severity.HIGH).status(Status.OPEN).reporter(u9).assignee(u10).project(p5).build();
			Issue i6 = Issue.builder().title("Chart not loading").description("Analytics chart fails on Safari").severity(Severity.LOW).status(Status.OPEN).reporter(u2).assignee(u7).project(p6).build();
			Issue i7 = Issue.builder().title("App crashes on startup").description("NullPointerException on launch").severity(Severity.CRITICAL).status(Status.IN_PROGRESS).reporter(u3).assignee(u8).project(p7).build();
			Issue i8 = Issue.builder().title("Payment double charged").description("Duplicate transactions recorded").severity(Severity.CRITICAL).status(Status.OPEN).reporter(u4).assignee(u9).project(p8).build();
			Issue i9 = Issue.builder().title("Search index delay").description("New issues not indexed quickly").severity(Severity.MEDIUM).status(Status.OPEN).reporter(u5).assignee(u10).project(p9).build();
			Issue i10 = Issue.builder().title("Report export slow").description("Excel export takes too long").severity(Severity.LOW).status(Status.OPEN).reporter(u6).assignee(u1).project(p10).build();
			Issue i11 = Issue.builder().title("Forgot password broken").description("Reset link invalid").severity(Severity.HIGH).status(Status.IN_PROGRESS).reporter(u7).assignee(u2).project(p1).build();
			Issue i12 = Issue.builder().title("Dark mode not applied").description("Theme switch not working").severity(Severity.LOW).status(Status.OPEN).reporter(u8).assignee(u3).project(p2).build();
			Issue i13 = Issue.builder().title("High CPU usage").description("API Gateway consumes 90% CPU").severity(Severity.CRITICAL).status(Status.OPEN).reporter(u9).assignee(u6).project(p3).build();
			Issue i14 = Issue.builder().title("Role not assigned").description("New users missing roles").severity(Severity.HIGH).status(Status.IN_PROGRESS).reporter(u10).assignee(u4).project(p4).build();
			Issue i15 = Issue.builder().title("SMS not delivered").description("Twilio API key expired").severity(Severity.HIGH).status(Status.OPEN).reporter(u1).assignee(u5).project(p5).build();
			Issue i16 = Issue.builder().title("Memory leak").description("Analytics module memory leak").severity(Severity.CRITICAL).status(Status.OPEN).reporter(u2).assignee(u7).project(p6).build();
			Issue i17 = Issue.builder().title("Push notifications missing").description("Mobile app not receiving notifications").severity(Severity.HIGH).status(Status.OPEN).reporter(u3).assignee(u8).project(p7).build();
			Issue i18 = Issue.builder().title("Payment timeout").description("Gateway times out after 60s").severity(Severity.CRITICAL).status(Status.OPEN).reporter(u4).assignee(u9).project(p8).build();
			Issue i19 = Issue.builder().title("Search results incorrect").description("Returning wrong issues").severity(Severity.MEDIUM).status(Status.IN_PROGRESS).reporter(u5).assignee(u10).project(p9).build();
			Issue i20 = Issue.builder().title("Report header missing").description("Generated PDF missing header").severity(Severity.LOW).status(Status.OPEN).reporter(u6).assignee(u1).project(p10).build();

			List<Issue> issues = issueRepository.saveAll(List.of(i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13,i14,i15,i16,i17,i18,i19,i20));

			// -------------------- COMMENTS --------------------
			Comment c1 = Comment.builder().issue(i1).user(u3).text("I also faced this bug!").build();
			Comment c2 = Comment.builder().issue(i1).user(u2).text("Working on a fix").build();
			Comment c3 = Comment.builder().issue(i2).user(u5).text("This only happens in Firefox").build();
			Comment c4 = Comment.builder().issue(i3).user(u7).text("Critical issue, needs urgent fix").build();
			Comment c5 = Comment.builder().issue(i4).user(u8).text("Security risk").build();
			Comment c6 = Comment.builder().issue(i5).user(u9).text("SMTP logs show connection refused").build();
			Comment c7 = Comment.builder().issue(i6).user(u2).text("Chart works fine in Chrome").build();
			Comment c8 = Comment.builder().issue(i7).user(u3).text("App crashes consistently").build();
			Comment c9 = Comment.builder().issue(i8).user(u4).text("Duplicate charge confirmed").build();
			Comment c10 = Comment.builder().issue(i9).user(u5).text("Indexing delay ~5 mins").build();
			Comment c11 = Comment.builder().issue(i10).user(u6).text("Export takes ~2 mins").build();
			Comment c12 = Comment.builder().issue(i11).user(u7).text("Password reset link invalid").build();
			Comment c13 = Comment.builder().issue(i12).user(u8).text("Dark mode ignored after reload").build();
			Comment c14 = Comment.builder().issue(i13).user(u9).text("CPU spikes on heavy load").build();
			Comment c15 = Comment.builder().issue(i14).user(u10).text("User roles missing in DB").build();
			Comment c16 = Comment.builder().issue(i15).user(u1).text("Twilio API key expired yesterday").build();
			Comment c17 = Comment.builder().issue(i16).user(u2).text("Memory leak after 1 hour").build();
			Comment c18 = Comment.builder().issue(i17).user(u3).text("Push notifications not received").build();
			Comment c19 = Comment.builder().issue(i18).user(u4).text("Payment gateway unstable").build();
			Comment c20 = Comment.builder().issue(i19).user(u5).text("Search results mismatch").build();

			commentRepository.saveAll(List.of(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,
					c11,c12,c13,c14,c15,c16,c17,c18,c19,c20));

			System.out.println("✅ Sample Data Inserted: 10 Users, 10 Projects, 20 Issues, 20 Comments");
		};
	}*/

}

