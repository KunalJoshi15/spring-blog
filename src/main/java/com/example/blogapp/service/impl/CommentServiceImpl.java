package com.example.blogapp.service.impl;

import com.example.blogapp.entity.Comment;
import com.example.blogapp.entity.Post;
import com.example.blogapp.exception.BlogApiException;
import com.example.blogapp.exception.ResourceNotFoundException;
import com.example.blogapp.payload.CommentDto;
import com.example.blogapp.repository.CommentRepository;
import com.example.blogapp.repository.PostRepository;
import com.example.blogapp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    private ModelMapper modelMapper;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper,
                              PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = this.modelMapper.map(commentDto, Comment.class);

        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",String.valueOf(postId)));

        comment.setPost(post);

        Comment newComment = commentRepository.save(comment);
        return this.modelMapper.map(newComment,CommentDto.class);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",String.valueOf(postId)));

        List<CommentDto> commentDtos = commentRepository.findByPostId(postId)
                .stream().map(comment -> this.modelMapper.map(comment,CommentDto.class))
                .collect(Collectors.toList());

        return commentDtos;
    }

    @Override
    public CommentDto getCommentsByCommentId(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",String.valueOf(postId)));
        Comment comment = commentRepository.findByPostIdAndId(postId,commentId);
        return this.modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public CommentDto updateCommentWithCommentId(long postId, long commentId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",String.valueOf(postId)));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("Comment","commentId",String.valueOf(commentId)));

        if(!comment.getPost().getId().equals(post.getId()))
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comments does not belong to specified post");

        comment.setName(commentDto.getName());
        comment.setId(commentId);
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());

        Comment save = commentRepository.save(comment);

        return this.modelMapper.map(save, CommentDto.class);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",String.valueOf(postId)));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("Comment","commentId",String.valueOf(commentId)));

        if(!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to specified post");
        }

        commentRepository.deleteById(commentId);
    }
}
