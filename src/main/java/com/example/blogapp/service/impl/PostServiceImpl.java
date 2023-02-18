package com.example.blogapp.service.impl;

import com.example.blogapp.entity.Post;
import com.example.blogapp.payload.PostDto;
import com.example.blogapp.repository.PostRepository;
import com.example.blogapp.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        // convert dto to entity.
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post returnPost = postRepository.save(post);

        // Entity to Dto
        PostDto returnDto = PostDto.builder()
                .id(returnPost.getId())
                .content(returnPost.getContent())
                .description(returnPost.getDescription())
                .title(returnPost.getTitle()).build();

        return returnDto;
    }

    @Override
    public List<PostDto> getAllPosts(Pageable pageable) {
        List<Post> allPosts = postRepository.findAll(pageable).getContent();
        // Entity to Dto conversion
        List<PostDto> allPostDtos = allPosts.stream().map(post -> {
            PostDto postDto = PostDto.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .description(post.getDescription()).build();

            return postDto;
        }).collect(Collectors.toList());
        return allPostDtos;
    }

}
