package com.example.sample1app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import com.example.sample1app.entity.Post;

@SpringBootApplication
public class SampleApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(SampleApp1Application.class, args);
	}
	
	@Bean
	public Post post() {
		return new Post(0,0,"Dummy","This is dummy post.");
	}

}
