package com.jiyong.yt.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jiyong.yt.dto.UserDTO;
import com.jiyong.yt.util.SHA256;
//[DB연결 사용법] 3. 인터페이스 받아서 클래스 생성
@Repository
public class UserDao implements IUserDao {
	// 컨테이너가 객체를 자동으로 생성 Autowired
	@Autowired
	public SqlSessionTemplate mybatis;
	
	@Override
	public int userInsert(UserDTO dto) {
		// TODO Auto-generated method stub
		dto.setUserPw(SHA256.getSHA256(dto.getUserPw()));
		return mybatis.insert("userMapper.userInsert", dto);
	}
	
	@Override
	public int userUpdate(UserDTO dto) {
		// TODO Auto-generated method stub
		return mybatis.update("userMapper.userModify", dto);
	}
	
	@Override
	public int userModify_FavorGroup(UserDTO dto) {
		// TODO Auto-generated method stub
		return mybatis.update("userMapper.userModify_FavorGroup", dto);
	}
	
	@Override
	public UserDTO userSelectOne(UserDTO dto) {
		// TODO Auto-generated method stub
		dto.setUserPw(SHA256.getSHA256(dto.getUserPw()));
		return mybatis.selectOne("userMapper.userSelectOne", dto);
	}
	
}
