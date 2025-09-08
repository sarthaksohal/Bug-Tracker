package com.project.bug_tracking_system.service;

import com.project.bug_tracking_system.entity.Project;
import com.project.bug_tracking_system.entity.User;
import com.project.bug_tracking_system.entity.dto.UserDto;
import com.project.bug_tracking_system.repository.ProjectRepository;
import com.project.bug_tracking_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
@Autowired
    private UserRepository userRepository;
@Autowired
private ProjectRepository projectRepository;
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
    public User getUserById(int id){
        return userRepository.findById(id).get();
    }

    public void updateUserById(int id, UserDto userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        if (userDto.getName() != null && !userDto.getName().isBlank()) {
            existingUser.setName(userDto.getName());
        }
        if (userDto.getEmail() != null && !userDto.getEmail().isBlank()) {
            existingUser.setEmail(userDto.getEmail());
        }
        if (userDto.getPassword() != null && !userDto.getPassword().isBlank()) {
            existingUser.setPassword(userDto.getPassword());
        }
        if (userDto.getUserRole() != null) {
            existingUser.setUserRole(userDto.getUserRole());
        }

        userRepository.save(existingUser);
    }

    public User getUserByEmail(String email) {
        User user=userRepository.findByEmail(email);
        return user;

    }

    public ResponseEntity<String> saveUser(UserDto userDto) {
        User user=User.builder()
                .password(userDto.getPassword())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .build();
        userRepository.save(user);
        return ResponseEntity.ok("saved successfully");
    }

    @Transactional
    public ResponseEntity<String> deleteUser(String emailId) {
        // Find the user
        User user = userRepository.findByEmail(emailId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Remove user from projects where they are participants
        List<Project> participatingProjects = user.getProjectList();
        for (Project project : participatingProjects) {
            project.getUserArrayList().remove(user); // ManyToMany list
            projectRepository.save(project);
        }

        // Optionally: set 'createdBy' in projects to null if the user created projects
        List<Project> createdProjects = user.getProjectList();
        for (Project project : createdProjects) {
            project.setCreatedBy(null); // requires nullable createdBy column
            projectRepository.save(project);
        }

        // Finally delete the user
        userRepository.delete(user);

        return ResponseEntity.ok("User deleted successfully, projects preserved");
    }

}
