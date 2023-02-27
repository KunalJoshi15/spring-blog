package com.example.blogapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:/application-test.properties")
class BlogAppApplicationTests {

	@Autowired
	PasswordEncoder passwordEncoder;
	@Test
	void contextLoads() {
	}

	@Test
	public void retrievePassword() {
		System.out.println(passwordEncoder.encode("admin"));
	}

}
