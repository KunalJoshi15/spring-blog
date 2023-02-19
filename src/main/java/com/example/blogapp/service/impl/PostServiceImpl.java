package com.example.blogapp.service.impl;

import com.example.blogapp.entity.Post;
import com.example.blogapp.exception.ResourceNotFoundException;
import com.example.blogapp.payload.PostDto;
import com.example.blogapp.repository.PostRepository;
import com.example.blogapp.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        // convert dto to entity.
        Post post = this.modelMapper.map(postDto, Post.class);
        Post returnPost = postRepository.save(post);

        // Entity to Dto
        PostDto returnDto = this.modelMapper.map(returnPost, PostDto.class);
        log.info("Created post with id {}",returnDto.getId());
        return returnDto;
    }

    @Override
    public List<PostDto> getAllPosts(Pageable pageable) {
        List<Post> allPosts = postRepository.findAll(pageable).getContent();
        // Entity to Dto conversion
        List<PostDto> allPostDtos = allPosts.stream().map(post ->
                this.modelMapper.map(post,PostDto.class))
                .collect(Collectors.toList());
        log.info("Fetched total posts {}",allPostDtos.size());
        return allPostDtos;
    }

    @Override
    public PostDto getPostById(long id) {
        Optional<Post> post = postRepository.findById(id);
        if(!post.isPresent()) {
            throw new ResourceNotFoundException("Post","postId",String.valueOf(id));
        }
        log.info("Found post with postId {}",post.get().getId());
        return this.modelMapper.map(post.get(), PostDto.class);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, long id) {
        // Dto to entity
        Optional<Post> post = postRepository.findById(id);
        if(!post.isPresent())
            throw new ResourceNotFoundException("Post","postId",String.valueOf(id));

        Post updatedPost = Post.builder()
                .id(id).title(postDto.getTitle())
                .description(postDto.getDescription())
                .content(postDto.getContent()).build();
        log.info("Updated post with postId: {}",updatedPost.getId());
        return this.modelMapper.map(postRepository.save(updatedPost), PostDto.class);
    }

    @Override
    public void deletePostById(long id) {
        Optional<Post> post = postRepository.findById(id);
        if(!post.isPresent())
            throw new ResourceNotFoundException("Post","postId",String.valueOf(id));

        postRepository.delete(post.get());
        log.info("Post successfully deleted with id: {}",post.get().getId());
    }

}
