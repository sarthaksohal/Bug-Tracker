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
	/*@Bean
	CommandLineRunner run(UserRepository userRepository,
						  ProjectRepository projectRepository,
						  IssueRepository issueRepository,
						  CommentRepository commentRepository) {
		return args -> {
			// ===== Users =====
			User u1 = User.builder().name("Alice").email("alice@example.com").password("pass1").userRole(UserRole.ADMIN).build();
			User u2 = User.builder().name("Bob").email("bob@example.com").password("pass2").userRole(UserRole.MANAGER).build();
			User u3 = User.builder().name("Charlie").email("charlie@example.com").password("pass3").userRole(UserRole.DEVELOPER).build();
			User u4 = User.builder().name("David").email("david@example.com").password("pass4").userRole(UserRole.DEVELOPER).build();
			User u5 = User.builder().name("Eve").email("eve@example.com").password("pass5").userRole(UserRole.ADMIN).build();
			User u6 = User.builder().name("Frank").email("frank@example.com").password("pass6").userRole(UserRole.DEVELOPER).build();
			User u7 = User.builder().name("Grace").email("grace@example.com").password("pass7").userRole(UserRole.REPORTER).build();
			User u8 = User.builder().name("Hank").email("hank@example.com").password("pass8").userRole(UserRole.DEVELOPER).build();
			User u9 = User.builder().name("Ivy").email("ivy@example.com").password("pass9").userRole(UserRole.TESTER).build();
			User u10 = User.builder().name("Jack").email("jack@example.com").password("pass10").userRole(UserRole.MANAGER).build();
			userRepository.saveAll(List.of(u1,u2,u3,u4,u5,u6,u7,u8,u9,u10));

			// ===== Projects with team members =====
			Project p1 = Project.builder().projectName("Bug Tracker").description("Tracking bugs for teams").createdBy(u1).userArrayList(List.of(u1,u2,u3)).build();
			Project p2 = Project.builder().projectName("E-Commerce").description("E-commerce web app").createdBy(u2).userArrayList(List.of(u2,u4,u5,u6)).build();
			Project p3 = Project.builder().projectName("School Portal").description("Portal for schools").createdBy(u3).userArrayList(List.of(u3,u7,u8)).build();
			Project p4 = Project.builder().projectName("Hospital System").description("Hospital management system").createdBy(u4).userArrayList(List.of(u4,u1,u9)).build();
			Project p5 = Project.builder().projectName("Chat App").description("Messaging app").createdBy(u5).userArrayList(List.of(u5,u2,u6,u10)).build();
			Project p6 = Project.builder().projectName("Travel Booking").description("Flight and hotel booking").createdBy(u6).userArrayList(List.of(u6,u3,u7,u8)).build();
			Project p7 = Project.builder().projectName("Food Delivery").description("Order food online").createdBy(u7).userArrayList(List.of(u7,u4,u9)).build();
			Project p8 = Project.builder().projectName("Banking App").description("Mobile banking app").createdBy(u8).userArrayList(List.of(u8,u1,u10)).build();
			Project p9 = Project.builder().projectName("Social Media").description("Photo sharing app").createdBy(u9).userArrayList(List.of(u9,u2,u3,u5)).build();
			Project p10 = Project.builder().projectName("Fitness Tracker").description("Track workouts").createdBy(u10).userArrayList(List.of(u10,u6,u7)).build();
			projectRepository.saveAll(List.of(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10));

			// ===== Issues =====
			Issue i1 = Issue.builder().title("Login bug").description("Login page not working").severity(Severity.HIGH).status(Status.OPEN).reporter(u2).project(p1).build();
			Issue i2 = Issue.builder().title("Checkout error").description("Payment gateway fails").severity(Severity.CRITICAL).status(Status.OPEN).reporter(u3).project(p2).build();
			Issue i3 = Issue.builder().title("Profile update bug").description("Profile picture not saving").severity(Severity.MEDIUM).status(Status.IN_PROGRESS).reporter(u4).project(p3).build();
			Issue i4 = Issue.builder().title("Attendance issue").description("Attendance not updating").severity(Severity.HIGH).status(Status.OPEN).reporter(u5).project(p4).build();
			Issue i5 = Issue.builder().title("Chat crash").description("App crashes on group chat").severity(Severity.CRITICAL).status(Status.OPEN).reporter(u6).project(p5).build();
			Issue i6 = Issue.builder().title("Slow dashboard").description("Dashboard loading slow").severity(Severity.LOW).status(Status.OPEN).reporter(u7).project(p6).build();
			Issue i7 = Issue.builder().title("Order bug").description("Food order not saving").severity(Severity.HIGH).status(Status.OPEN).reporter(u8).project(p7).build();
			Issue i8 = Issue.builder().title("Transaction failed").description("Bank transfer fails sometimes").severity(Severity.CRITICAL).status(Status.OPEN).reporter(u9).project(p8).build();
			Issue i9 = Issue.builder().title("Feed not loading").description("Newsfeed blank on refresh").severity(Severity.MEDIUM).status(Status.IN_PROGRESS).reporter(u10).project(p9).build();
			Issue i10 = Issue.builder().title("Workout bug").description("Steps counter inaccurate").severity(Severity.LOW).status(Status.OPEN).reporter(u1).project(p10).build();
			issueRepository.saveAll(List.of(i1,i2,i3,i4,i5,i6,i7,i8,i9,i10));

			// ===== Comments =====
			Comment c1 = Comment.builder().text("I am working on this issue").user(u2).issue(i1).build();
			Comment c2 = Comment.builder().text("Payment API needs update").user(u3).issue(i2).build();
			Comment c3 = Comment.builder().text("Frontend validation missing").user(u4).issue(i3).build();
			Comment c4 = Comment.builder().text("Database error found").user(u5).issue(i4).build();
			Comment c5 = Comment.builder().text("Logs show null pointer").user(u6).issue(i5).build();
			Comment c6 = Comment.builder().text("Need more info").user(u7).issue(i6).build();
			Comment c7 = Comment.builder().text("Order service restart fixed partial issue").user(u8).issue(i7).build();
			Comment c8 = Comment.builder().text("Bank API key expired").user(u9).issue(i8).build();
			Comment c9 = Comment.builder().text("Frontend team is checking feed issue").user(u10).issue(i9).build();
			Comment c10 = Comment.builder().text("Sensor calibration required").user(u1).issue(i10).build();
			Comment c11 = Comment.builder().text("This bug is reproducible only on mobile").user(u3).issue(i1).build();
			Comment c12 = Comment.builder().text("Trying with latest API version").user(u5).issue(i2).build();
			Comment c13 = Comment.builder().text("Backend logs suggest timeout").user(u6).issue(i3).build();
			Comment c14 = Comment.builder().text("Attendance module needs redesign").user(u7).issue(i4).build();
			Comment c15 = Comment.builder().text("App crash stack trace uploaded").user(u8).issue(i5).build();
			Comment c16 = Comment.builder().text("Dashboard slow due to large queries").user(u9).issue(i6).build();
			Comment c17 = Comment.builder().text("Investigating cache issue").user(u10).issue(i7).build();
			Comment c18 = Comment.builder().text("Bank API issue reported to vendor").user(u1).issue(i8).build();
			Comment c19 = Comment.builder().text("Feed issue seems browser-specific").user(u2).issue(i9).build();
			Comment c20 = Comment.builder().text("Step counter bug linked to device settings").user(u4).issue(i10).build();

			commentRepository.saveAll(
					List.of(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,
							c11,c12,c13,c14,c15,c16,c17,c18,c19,c20)
			);
		};

}*/
}
