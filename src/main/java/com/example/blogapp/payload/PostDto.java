package com.example.blogapp.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> comments;
}
