package com.jiyong.yt.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.jiyong.yt.dao.IUserDao;
import com.jiyong.yt.dto.UserDTO;
//[DB연결 사용법] 6. 서비스 클래스 작성
@Service("IUserService")
public class UserService implements IUserService {
	// 인터페이스로 생성해야함
	@Autowired
	public IUserDao dao;
	
	@Override
	public int userInsert(UserDTO dto) {
		return dao.userInsert(dto);
	}
	@Override
	public UserDTO userSelectOne(UserDTO dto) {
		return dao.userSelectOne(dto);
	}
	@Override
	public int userUpdate(UserDTO dto) {
		return dao.userUpdate(dto);
	}
	@Override
	public int userModify_FavorGroup(UserDTO dto) {
		// TODO Auto-generated method stub
		return dao.userModify_FavorGroup(dto);
	}
}
