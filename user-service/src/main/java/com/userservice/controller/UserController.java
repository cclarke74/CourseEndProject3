package com.userservice.controller;


import com.userservice.dto.UserEntityRequest;
import com.userservice.entity.UserEntity;
import com.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test(){
        return  "User service works";
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserEntity>> listUsers() {
        List<UserEntity> userList = this.userService.listUsers();
            return ResponseEntity.ok(userList);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody UserEntityRequest user) {
        try {
            this.userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            System.out.println("--->");
            System.out.println(e.getMessage());
            System.out.println("--->");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/search_by_id/{id}")
    public ResponseEntity<UserEntity> searchUserById(@PathVariable Long id) {
        UserEntity user = this.userService.findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserEntity());
        }
    }
    @GetMapping("/search_user_name/{username}")
    public ResponseEntity<?> searchUserByUserName(@PathVariable String username) {
        UserEntity user = this.userService.findUserByUserName(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            String errorMessage = "User with Username " + username + " not found";
            return ResponseEntity.ok(new ErrorResponse(errorMessage));
        }
    }

    static class ErrorResponse {
        private final String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    @PutMapping("/update_user/{id}")
    public ResponseEntity<Void> updateUser(@RequestBody UserEntityRequest userEntityRequest, @PathVariable Long id) {
        try {
            this.userService.updateUser(id, userEntityRequest);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/delete_user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            this.userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
