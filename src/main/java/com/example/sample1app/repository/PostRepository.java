package com.example.sample1app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sample1app.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

		public Optional<Post> findById(int id);
}
