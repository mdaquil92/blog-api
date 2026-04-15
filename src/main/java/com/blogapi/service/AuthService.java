package com.blogapi.service;

import com.blogapi.dto.LoginDto;
import com.blogapi.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
