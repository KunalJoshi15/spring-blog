package com.example.blogapp.service;

import com.example.blogapp.payload.PostDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    public PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts(Pageable pageable);

    PostDto getPostById(long id);

    PostDto updatePostById(PostDto postDto, long id);

    void deletePostById(long id);
}
