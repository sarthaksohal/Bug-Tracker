package com.project.bug_tracking_system.controller;

import com.project.bug_tracking_system.entity.User;
import com.project.bug_tracking_system.entity.dto.LoginBody;
import com.project.bug_tracking_system.entity.dto.UserDto;
import com.project.bug_tracking_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("get/all")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/update/id")
    public ResponseEntity<?> updateUserById(@PathVariable int id, UserDto userDto){
        userService.updateUserById(id,userDto);
        return ResponseEntity.ok("updated successfully");
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginCheck(@RequestBody LoginBody loginBody) {
        User user = userService.getUserByEmail(loginBody.getEmail());
        if (user == null) {
            return new ResponseEntity<>("Email not found", HttpStatus.NOT_FOUND);
        }

        if (user.getPassword().equals(loginBody.getPassword())) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Password or email is wrong", HttpStatus.UNAUTHORIZED);
        }
    }
        @GetMapping("/project/{id}")
        public ResponseEntity<?> getProjectForUser(@PathVariable int id){

         User user = userService.getUserById(id);
            if (user == null) {
                return new ResponseEntity<>("Email not found", HttpStatus.NOT_FOUND);
            }

           return new ResponseEntity<>(user.getProjectList(),HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto){
        return userService.saveUser(userDto);
    }
@DeleteMapping("/delete")
    public  ResponseEntity<String> deleteUserByEmailId(@RequestParam String email){
        return userService.deleteUser(email);
}




}
