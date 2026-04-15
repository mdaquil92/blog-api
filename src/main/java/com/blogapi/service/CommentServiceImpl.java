package com.blogapi.service;

import com.blogapi.dto.CommentDto;
import com.blogapi.entity.Comment;
import com.blogapi.entity.Post;
import com.blogapi.exception.BlogApiException;
import com.blogapi.exception.ResourceNotFoundException;
import com.blogapi.repository.CommentRepository;
import com.blogapi.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper mapper;

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment updatedComment = commentRepository.save(comment);
        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        commentRepository.delete(comment);
    }

    // ========== Helper Methods ==========
    private CommentDto mapToDto(Comment comment) {
        return mapper.map(comment, CommentDto.class);
    }

    private Comment mapToEntity(CommentDto commentDto) {
        return mapper.map(commentDto, Comment.class);
    }
}
