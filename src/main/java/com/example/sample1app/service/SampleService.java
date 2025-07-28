package com.example.sample1app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.sample1app.entity.Post;
import com.example.sample1app.repository.PostRepository;

@Service
public class SampleService {
	
	private String baseUrl = "https://jsonplaceholder.typicode.com/posts";
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	PostRepository repository;
	
	public Post getPost() {
		return new Post(0,0,"Dummy","This is sample.");
	}
	
	public Post[] getAllPosts() {
		return restTemplate.getForObject(baseUrl, Post[].class);
	}
	
	public Post getPost2(int id) {
		return restTemplate.getForObject(baseUrl+"/"+id, Post.class);
	}
	
	public Object[] getLocalPosts() {
		return repository.findAll().toArray();
	}
	
	public Post getAndSavePost(int id) {
		Post post = restTemplate.getForObject(baseUrl + "/"+id, Post.class);
		repository.save(post);
		return post;
	}
}
