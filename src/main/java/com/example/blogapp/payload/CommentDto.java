package com.example.blogapp.payload;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDto {
    private Long id;
    @NotEmpty(message = "Name should not be empty/null")
    private String name;

    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;
    @NotEmpty
    private String body;
    @JsonBackReference
    private PostDto postDto;
}
