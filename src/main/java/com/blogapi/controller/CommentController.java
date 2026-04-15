package com.blogapi.controller;

import com.blogapi.dto.CommentDto;
import com.blogapi.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@AllArgsConstructor
@Tag(name = "Comment REST APIs", description = "Create, Read, Update, Delete Comments")
public class CommentController {

    private CommentService commentService;

    // POST /api/posts/{postId}/comments
    @Operation(summary = "Add a comment to a post", security = @SecurityRequirement(name = "Bearer Authentication"))
    @PostMapping
    public ResponseEntity<CommentDto> createComment(
            @PathVariable Long postId,
            @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    // GET /api/posts/{postId}/comments
    @Operation(summary = "Get all comments for a post")
    @GetMapping
    public List<CommentDto> getCommentsByPostId(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    // GET /api/posts/{postId}/comments/{commentId}
    @Operation(summary = "Get a single comment by ID")
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(
            @PathVariable Long postId,
            @PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
    }

    // PUT /api/posts/{postId}/comments/{commentId}
    @Operation(summary = "Update a comment", security = @SecurityRequirement(name = "Bearer Authentication"))
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @Valid @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.updateComment(postId, commentId, commentDto));
    }

    // DELETE /api/posts/{postId}/comments/{commentId}
    @Operation(summary = "Delete a comment", security = @SecurityRequirement(name = "Bearer Authentication"))
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId) {
        commentService.deleteComment(postId, commentId);
        return ResponseEntity.ok("Comment deleted successfully.");
    }
}
