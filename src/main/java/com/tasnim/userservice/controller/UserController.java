package com.tasnim.userservice.controller;


import com.tasnim.userservice.dto.UserRequestDto;
import com.tasnim.userservice.enums.Role;
import com.tasnim.userservice.service.CustomUserDetailsService;
import com.tasnim.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;

    public UserController(UserService userService, CustomUserDetailsService customUserDetailsService) {
        this.userService = userService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequestDto userRequestDto){
        userRequestDto.setRole(Role.USER);
        return ResponseEntity.ok(userService.registerUser(userRequestDto));
    }

    @PostMapping("/admin/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerAdmin(@RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(userService.registerUser(userRequestDto));
    }

    @GetMapping("/user/info/{username}")
    public ResponseEntity<?> getUserInfo(@PathVariable String username){
        return ResponseEntity.ok(customUserDetailsService.loadUserByUsername(username));
    }
}
