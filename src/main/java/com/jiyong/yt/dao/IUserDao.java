package com.jiyong.yt.dao;

import com.jiyong.yt.dto.UserDTO;

// [DB연결 사용법] 2. Dao인터페이스 생성
public interface IUserDao {
	public int userInsert (UserDTO dto);	// 사용할 추상메소드 정의
	public UserDTO userSelectOne(UserDTO dto);
	public int userUpdate(UserDTO dto);
	public int userModify_FavorGroup(UserDTO dto);
}
