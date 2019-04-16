package com.example.demo.entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book", schema = "bookdatabase")
public class Book {
	@Id
	@Column(name = "uid", nullable = false)
	@NotNull(message = "BookId should not be empty")
	int uid;

	@Column(name = "code", nullable = false)
	@NotEmpty(message = "Code should not be empty")
	@Size(max = 20, message = "Size of code should not be greater than 20")
	String code;

	@Column(name = "name", nullable = false)
	@NotEmpty(message = "Name should not be empty")
	@Size(max = 255, message = "Size of name should not be greater than 255")
	String name;

	@Column(name = "description")
	@Size(max = 500, message = "Size of description should not be greate than 500")
	String description;

	@Column(name = "category", nullable = false)
	@NotEmpty(message = "Category should not be empty")
	@Size(max = 255, message = "Size of category should not be greater than 255")
	String category;

	@Column(name = "author", nullable = false)
	@NotEmpty(message = "Author should not be empty")
	@Size(max = 255, message = "Size of author should not be greater than 255")
	String author;

	@Column(name = "publisher")
	@Size(max = 255, message = "Size of publisher should not be greater than 255")
	String publisher;

	@Column(name = "create_user", nullable = false)
	@NotEmpty(message = "Create User should not be empty")
	@Size(max = 255, message = "Size of create user should not be greater than 100")
	String createUser;

	@Column(name = "create_date", nullable = false)
	@CreationTimestamp
	ZonedDateTime createDate;

	@Column(name = "update_user", nullable = false)
	@NotEmpty(message = "Update user should not be empty")
	@Size(max = 255, message = "Size of update user should not be greater than 100")
	String updateUser;

	@Column(name = "update_date")
	@UpdateTimestamp
	ZonedDateTime updateDate;
}
