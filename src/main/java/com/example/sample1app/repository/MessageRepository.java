package com.example.sample1app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sample1app.entity.MessageEntity;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long>{
	public Optional<MessageEntity> findById(Long id);
	public Optional<MessageEntity> findByContent(String content);
}
