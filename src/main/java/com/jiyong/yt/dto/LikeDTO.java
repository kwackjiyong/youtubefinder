package com.jiyong.yt.dto;

public class LikeDTO {
	private int likeId;
	private int channelId;
	private String userId;
	private int likeWhether;
	private java.sql.Timestamp likeDate;
	
	public int getLikeId() {
		return likeId;
	}
	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public int getLikeWhether() {
		return likeWhether;
	}
	public void setLikeWhether(int likeWhether) {
		this.likeWhether = likeWhether;
	}
	public java.sql.Timestamp getLikeDate() {
		return likeDate;
	}
	public void setLikeDate(java.sql.Timestamp likeDate) {
		this.likeDate = likeDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
