package com.jiyong.yt.service;

import com.jiyong.yt.dto.UserDTO;

// [DB연결 사용법] 5. 서비스 인터페이스 작성
public interface IUserService {
	public int userInsert(UserDTO dto);
	public UserDTO userSelectOne(UserDTO dto);
	public int userUpdate(UserDTO dto);
	public int userModify_FavorGroup(UserDTO dto);
}
