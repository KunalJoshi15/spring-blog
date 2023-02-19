package com.example.blogapp.controller;

import com.example.blogapp.payload.PostDto;
import com.example.blogapp.service.PostService;
import com.example.blogapp.utils.GenerateDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PostController.class)
public class PostControllerTests {

    @MockBean
    PostService postService;

    @Autowired
    MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    PostDto postDto, postDto1;

    @BeforeEach
    public void setup() {
        postDto = GenerateDto.postDto();
        postDto1 = GenerateDto.postDto();
    }

    @Test
    @DisplayName("Test Create Post with Dtos")
    public void testCreatePost() throws Exception {
        String request = objectMapper.writeValueAsString(postDto);
        Mockito.when(postService.createPost(ArgumentMatchers.any()))
                .thenReturn(postDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/posts")
                        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                        .content(request);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        String response = mvcResult.getResponse().getContentAsString();

        Assertions.assertEquals(request, response);
    }

    @Test
    @DisplayName("Get all post route")
    public void testGetAllPosts() throws Exception {
        List<PostDto> postDtos = List.of(postDto,postDto1);

        Mockito.when(postService.getAllPosts(ArgumentMatchers.any()))
                .thenReturn(postDtos);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andReturn();

        Assertions.assertEquals(objectMapper.writeValueAsString(postDtos),
                objectMapper.writeValueAsString(List.of(postDto,postDto1)));
    }

}
