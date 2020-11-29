package com.jiyong.yt.dto;
public class UserDTO {		// 유저테이블
	
	private String userId;// 사용자 아이디
	private String userName; // 사용자 이름
	private String userPw; //패스워드
	private String userEmail; // 이메일주소
	private java.sql.Date userBirth; //생년월일
	private int userGender; //사용자 성별
	private int userClass; // 사용자 권한
	private String favorTagGroup; // 선호 태그 그룹
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public java.sql.Date getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(java.sql.Date userBirth) {
		this.userBirth = userBirth;
	}
	public int getUserGender() {
		return userGender;
	}
	public void setUserGender(int userGender) {
		this.userGender = userGender;
	}
	public int getUserClass() {
		return userClass;
	}
	public void setUserClass(int userClass) {
		this.userClass = userClass;
	}
	public String getFavorTagGroup() {
		return favorTagGroup;
	}
	public void setFavorTagGroup(String favorTagGroup) {
		this.favorTagGroup = favorTagGroup;
	}
}
