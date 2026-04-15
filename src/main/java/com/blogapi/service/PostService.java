package com.blogapi.service;

import com.blogapi.dto.PostDto;
import com.blogapi.dto.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto getPostById(Long id);
    PostDto updatePost(PostDto postDto, Long id);
    void deletePostById(Long id);
}
