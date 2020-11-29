package com.jiyong.yt.dto;

public class BlockTagDTO {
	private String tagName;
	private java.sql.Timestamp blockDate;
	private String blockReason;
	
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public java.sql.Timestamp getBlockDate() {
		return blockDate;
	}
	public void setBlockDate(java.sql.Timestamp blockDate) {
		this.blockDate = blockDate;
	}
	public String getBlockReason() {
		return blockReason;
	}
	public void setBlockReason(String blockReason) {
		this.blockReason = blockReason;
	}
}
