package com.tasnim.userservice.service;

import com.tasnim.userservice.dto.UserRequestDto;
import com.tasnim.userservice.dto.UserResponseDto;

public interface UserService {
    UserResponseDto registerUser(UserRequestDto userRequestDto);
}
