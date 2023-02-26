package com.example.blogapp.controller;

import com.example.blogapp.payload.PostDto;
import com.example.blogapp.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Slf4j
public class PostController {

    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(@RequestParam(required = false, defaultValue = "0") int pageNo,
                                                     @RequestParam(required = false, defaultValue = "5") int pageSize) {
        return new ResponseEntity<>(postService.getAllPosts(PageRequest.of(pageNo,pageSize)),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(postService.getPostById(id),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(
            @RequestBody PostDto postDto,
            @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(postService.updatePostById(postDto, id),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostById(
            @PathVariable(name = "id") long id
    ) {
        postService.deletePostById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
