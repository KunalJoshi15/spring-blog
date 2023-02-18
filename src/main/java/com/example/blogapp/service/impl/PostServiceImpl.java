package com.example.blogapp.service.impl;

import com.example.blogapp.payload.PostDto;
import com.example.blogapp.repository.PostRepository;
import com.example.blogapp.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        // convert dto to entity.
        return null;
    }
}
