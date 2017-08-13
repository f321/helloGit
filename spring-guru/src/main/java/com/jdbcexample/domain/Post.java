package com.jdbcexample.domain;

public class Post {

	private int id;
	private String message;

	public Post(int int1, String string) {
		id = int1;
		message = string;
	}

	public Post() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", message=" + message + "]";
	}
}
