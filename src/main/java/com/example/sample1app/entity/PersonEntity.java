package com.example.sample1app.entity;

import com.example.sample1app.annotation.Phone;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import jakarta.persistence.NamedQuery;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

@Entity
@NamedQueries({
		@NamedQuery(name="findWithName", query="from PersonEntity where name like :fname"),
		@NamedQuery(name="findWithId", query="from PersonEntity where name like :fid"),
		@NamedQuery(name="findByAge",query="from PersonEntity where age >= :min and age < :max")
})
@Table(name="person")
public class PersonEntity {
	
	@OneToMany(mappedBy="person1")
	@Column(nullable = true)
	private List<MessageEntity> messages;
	
	public List<MessageEntity> getMessages(){
		return  messages;
	};
	
	public void setMessages(List<MessageEntity> messages) {
		this.messages = messages;
	}
	
	  @Id
	  //@GeneratedValue(strategy = GenerationType.AUTO)
	  @Column
	  @NotNull
	  @Min(1)
	  private long id;
	  
	  //@NotBlank(message="テストです")
	  @Pattern(regexp="[a-zA-Zaあ-ん]+")
	  @Column(length = 50, nullable = false)
	  private String name;

	  @Column(length = 200, nullable = true)
	  @Email(message="メールアドレスを入力してね")
	  private String mail;

	  @Column(nullable = true)
	  @Min(0)
	  @Max(200)
	  private Integer age;
	  
	  @Column(nullable = true)
	  @Phone(onlyNumber=true)
	  private String memo;

	  public long getId() {
	    return id;
	  }
	  public void setId(long id) {
	    this.id = id;
	  }

	  public String getName() {
	    return name;
	  }
	  public void setName(String name) {
	    this.name = name;
	  }

	  public String getMail() {
	    return mail;
	  }
	  public void setMail(String mail) {
	    this.mail = mail;
	  }

	  public Integer getAge() {
	    return age;
	  }
	  public void setAge(Integer age) {
	    this.age = age;
	  }

	  public String getMemo() {
	    return memo;
	  }
	  public void setMemo(String memo) {
	    this.memo = memo;
	  }

}
