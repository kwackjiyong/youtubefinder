package com.jiyong.yt.dto;

public class ChannelDTO {
	private int channelId;
	private String channelName;
	private String channelExp;
	private int block_whether;
	private java.sql.Timestamp registerDate;
	//좋아요 집계값 담을 멤버
	private int likeCnt;
	private int dislikeCnt;
	//관련도 집계값을 담을 멤버
	private int relevance;
	
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public int getBlock_whether() {
		return block_whether;
	}
	public void setBlock_whether(int block_whether) {
		this.block_whether = block_whether;
	}
	public java.sql.Timestamp getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(java.sql.Timestamp registerDate) {
		this.registerDate = registerDate;
	}
	public String getChannelExp() {
		return channelExp;
	}
	public void setChannelExp(String channelExp) {
		this.channelExp = channelExp;
	}
	public int getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	public int getDislikeCnt() {
		return dislikeCnt;
	}
	public void setDislikeCnt(int dislikeCnt) {
		this.dislikeCnt = dislikeCnt;
	}
	public int getRelevance() {
		return relevance;
	}
	public void setRelevance(int relevance) {
		this.relevance = relevance;
	}
}
