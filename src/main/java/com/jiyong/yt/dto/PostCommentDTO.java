package com.jiyong.yt.dto;

public class PostCommentDTO {
	private int commentId;
	private int postId;
	private String userId;
	private String contents;
	private java.sql.Timestamp commentDate;
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
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
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public java.sql.Timestamp getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(java.sql.Timestamp commentDate) {
		this.commentDate = commentDate;
	}
	
}
