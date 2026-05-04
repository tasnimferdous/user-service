package com.tasnim.userservice.service.Impl;

import com.tasnim.userservice.dto.UserRequestDto;
import com.tasnim.userservice.dto.UserResponseDto;
import com.tasnim.userservice.entity.Users;
import com.tasnim.userservice.repository.UserDetailsRepository;
import com.tasnim.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder) {
        this.userDetailsRepository = userDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        if(userDetailsRepository.findByUsername(userRequestDto.getUsername()).isPresent()){
            throw new RuntimeException("Username already exists: " + userRequestDto.getUsername());
        }

        Users user = Users.builder()
                .username(userRequestDto.getUsername())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .role(userRequestDto.getRole())
                .build();
        Users savedUser = userDetailsRepository.save(user);
        log.info("User registered successfully: {}", savedUser.getUsername());

        return UserResponseDto.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .role(savedUser.getRole())
                .build();
    }
}
