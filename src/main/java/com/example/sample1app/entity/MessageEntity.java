package com.example.sample1app.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Entity
@Table(name= "msgdata2")
@Data
public class MessageEntity {
	@Id
	@NotNull
	private long id;
	
	@NotNull
	@Column(name="person_id")
	private long person_id;
	
	@Column(nullable = false, name="content")
	@NotBlank
	private String content;
	
	@Column(name="datetime")
	private Date datetime;
	
	@ManyToOne
	@JoinColumn(name="id",insertable=false,updatable=false)
	private PersonEntity person1;
}
