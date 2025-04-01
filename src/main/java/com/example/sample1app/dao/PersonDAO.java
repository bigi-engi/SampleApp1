package com.example.sample1app.dao;

import java.io.Serializable;
import java.util.List;

public interface PersonDAO <T> extends Serializable{
	
	public List<T> getAll();
	public List<T> getAll2();
	public T findById(long id);
	public List<T> findByName(String name);
	public List<T> findByName2(String name);
	public List<T> find2(String fstr);
	public List<T> find3(String fstr);
	public List<T> findByAge(int min, int max);
	public List<T> getPage(int page, int limit);
}