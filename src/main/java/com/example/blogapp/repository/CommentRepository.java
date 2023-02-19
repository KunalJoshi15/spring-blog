package com.example.blogapp.repository;

import com.example.blogapp.entity.Comment;
import com.example.blogapp.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(long postId);

    Comment findByPostIdAndId(long postId,long commentId);
}
