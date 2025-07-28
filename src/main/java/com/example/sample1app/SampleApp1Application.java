package com.example.sample1app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.sample1app.entity.Post;

@SpringBootApplication
public class SampleApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(SampleApp1Application.class, args);
	}
}
