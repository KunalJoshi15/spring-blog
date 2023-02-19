package com.example.blogapp.utils;

import com.example.blogapp.payload.PostDto;
import net.datafaker.Faker;

public class GenerateDto {

    static Faker faker = new Faker();

    public static PostDto postDto() {
        PostDto postDto = PostDto.builder()
                .title(faker.artist().name())
                .description(faker.chuckNorris().fact())
                .content(faker.esports().team())
                .build();

        return postDto;
    }
}
