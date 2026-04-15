package com.blogapi.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PostDto {

    private Long id;

    @NotEmpty(message = "Post title cannot be empty")
    @Size(min = 2, message = "Post title must have at least 2 characters")
    private String title;

    @NotEmpty(message = "Post description cannot be empty")
    @Size(min = 10, message = "Post description must have at least 10 characters")
    private String description;

    @NotEmpty(message = "Post content cannot be empty")
    private String content;

    private Set<CommentDto> comments;

    private Long categoryId;
}
