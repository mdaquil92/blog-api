package com.blogapi.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CategoryDto {
    private Long id;

    @NotEmpty(message = "Category name cannot be empty")
    private String name;

    private String description;
}
