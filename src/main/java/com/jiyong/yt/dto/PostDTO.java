package com.jiyong.yt.dto;

public class PostDTO {
	private int postId;
	private String userId;
	private String postTitle;
	private String postContents;
	private java.sql.Timestamp postingDate;
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostContents() {
		return postContents;
	}
	public void setPostContents(String postContents) {
		this.postContents = postContents;
	}
	public java.sql.Timestamp getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(java.sql.Timestamp postingDate) {
		this.postingDate = postingDate;
	}
}
