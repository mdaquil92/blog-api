package com.blogapi.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LoginDto {
    @NotEmpty(message = "Username or email cannot be empty")
    private String usernameOrEmail;

    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
