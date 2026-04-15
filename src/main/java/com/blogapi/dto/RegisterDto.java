package com.blogapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RegisterDto {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3, message = "Username must have at least 3 characters")
    private String username;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;
}
