package com.blogapi.controller;

import com.blogapi.dto.PostDto;
import com.blogapi.dto.PostResponse;
import com.blogapi.service.PostService;
import com.blogapi.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
@Tag(name = "Post REST APIs", description = "Create, Read, Update, Delete Posts")
public class PostController {

    private PostService postService;

    // POST /api/posts  (Admin only)
    @Operation(summary = "Create a new post", security = @SecurityRequirement(name = "Bearer Authentication"))
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    // GET /api/posts?pageNo=0&pageSize=10&sortBy=id&sortDir=asc
    @Operation(summary = "Get all posts with pagination and sorting")
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo",   defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE,   required = false) int pageSize,
            @RequestParam(value = "sortBy",   defaultValue = AppConstants.DEFAULT_SORT_BY,     required = false) String sortBy,
            @RequestParam(value = "sortDir",  defaultValue = AppConstants.DEFAULT_SORT_DIR,    required = false) String sortDir
    ) {
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    // GET /api/posts/{id}
    @Operation(summary = "Get a single post by ID")
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // PUT /api/posts/{id}  (Admin only)
    @Operation(summary = "Update a post by ID", security = @SecurityRequirement(name = "Bearer Authentication"))
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(
            @Valid @RequestBody PostDto postDto,
            @PathVariable Long id) {
        return ResponseEntity.ok(postService.updatePost(postDto, id));
    }

    // DELETE /api/posts/{id}  (Admin only)
    @Operation(summary = "Delete a post by ID", security = @SecurityRequirement(name = "Bearer Authentication"))
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.deletePostById(id);
        return ResponseEntity.ok("Post deleted successfully.");
    }
}
