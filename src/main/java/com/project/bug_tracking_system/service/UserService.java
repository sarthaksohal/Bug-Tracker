package com.project.bug_tracking_system.service;

import com.project.bug_tracking_system.entity.User;
import com.project.bug_tracking_system.entity.dto.UserDto;
import com.project.bug_tracking_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
@Autowired
    private UserRepository userRepository;

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

    public ResponseEntity<String> deleteUser(String emailId) {
        userRepository.deleteByEmail(emailId);
        return ResponseEntity.ok("deleted successfully");
    }
}
