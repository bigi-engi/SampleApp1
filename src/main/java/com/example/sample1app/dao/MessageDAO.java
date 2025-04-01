package com.example.sample1app.dao;

import java.io.Serializable;
import java.util.List;

import com.example.sample1app.entity.MessageEntity;

public interface MessageDAO<T> extends Serializable {
	public List<T> getAll();
	public List<T> getPage(int page,int limit);
	public MessageEntity findById(long id);
	public List<T> findByName(String name);
	public List<T> find(String fstr);
}
