package com.markjr.cli.controller;

import com.markjr.cli.aop.ValidateUserExists;
import com.markjr.cli.model.User;
import com.markjr.cli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("initUser")
    public ResponseEntity<?> initUser() throws IOException {
        userService.setupUser();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
        List<User> userList = userService.getAllUser();
        return ResponseEntity.ok().body(userList);
    }

    @GetMapping("/users/{userId}")
    @ValidateUserExists
    public ResponseEntity<User> findUserByUserId(@PathVariable Long userId) {
        User user = userService.findUserByUserId(userId);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public ResponseEntity<User> insertUser(@RequestBody User request) {
        User newUser = userService.insertUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping("/users/{userId}")
    @ValidateUserExists
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User request) {
        User updateUser = userService.updateUser(userId, request);
        return ResponseEntity.ok().body(updateUser);
    }

    @DeleteMapping("/users/{userId}")
    @ValidateUserExists
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
