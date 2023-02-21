package com.example.blogapp.controller;

import com.example.blogapp.payload.CommentDto;
import com.example.blogapp.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId,commentDto),
                HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsWithPostId(
            @PathVariable(value = "postId") long postId
    ) {
        return new ResponseEntity<>(commentService.getCommentsByPostId(postId),
                HttpStatus.OK);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentForPostWithCommentId(
            @PathVariable(name = "postId") long postId,
            @PathVariable(name = "commentId") long commentId
    ) {
        return new ResponseEntity<>(commentService.getCommentsByCommentId(postId,commentId),
                HttpStatus.OK);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentWithCommentId(
            @PathVariable(name = "postId") long postId,
            @PathVariable(name = "commentId") long commentId,
            @RequestBody CommentDto commentDto
    ) {
        return new ResponseEntity<>(commentService.updateCommentWithCommentId(postId,commentId,commentDto),
                HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable(name = "postId") long postId,
            @PathVariable(name = "commentId") long commentId
    ) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
