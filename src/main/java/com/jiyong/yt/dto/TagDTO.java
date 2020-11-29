package com.jiyong.yt.dto;

public class TagDTO {
	private int tagId;
	private int channelId;
	private String tagName;
	private java.sql.Timestamp taggingDate;
	private String userId;
	private int tagCount;
	
	public int getTagId() {
		return tagId;
	}
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public java.sql.Timestamp getTaggingDate() {
		return taggingDate;
	}
	public void setTaggingDate(java.sql.Timestamp taggingDate) {
		this.taggingDate = taggingDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getTagCount() {
		return tagCount;
	}
	public void setTagCount(int tagCount) {
		this.tagCount = tagCount;
	}
}
