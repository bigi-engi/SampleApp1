package com.example.sample1app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="posts")
public class Post {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	@NotNull
	public int id;
	
	@Column
	@NotNull
	public int user_id;
	
	@Column
	public String title;
	
	@Column
	public String body;
	
	public Post() {
		this(0,0,"","");
	}
	public Post(int id, int user_id, String title, String body) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.title = title;
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "{id:"+id+", userId:"+user_id+", title"+title+", body:"+body+"}";
	}
}
