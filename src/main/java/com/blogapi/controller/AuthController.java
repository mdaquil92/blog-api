package com.blogapi.controller;

import com.blogapi.dto.JwtAuthResponse;
import com.blogapi.dto.LoginDto;
import com.blogapi.dto.RegisterDto;
import com.blogapi.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Tag(name = "Auth REST APIs", description = "Login and Register APIs")
public class AuthController {

    private AuthService authService;

    // POST /api/auth/login
    @Operation(summary = "Login user and get JWT token")
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    // POST /api/auth/register
    @Operation(summary = "Register a new user")
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
