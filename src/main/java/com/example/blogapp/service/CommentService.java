package com.example.blogapp.service;

import com.example.blogapp.payload.CommentDto;

import java.util.List;
import java.util.Set;

public interface CommentService {
    CommentDto createComment(long postId,CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentsByCommentId(long postId, long commentId);

    CommentDto updateCommentWithCommentId(long postId, long commentId, CommentDto commentDto);

    void deleteComment(long postId, long commentId);
}
