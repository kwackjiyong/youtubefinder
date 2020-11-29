package com.jiyong.yt.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jiyong.yt.dto.PostDTO;
import com.jiyong.yt.dto.TagDTO;
//[DB연결 사용법] 3. 인터페이스 받아서 클래스 생성
@Repository
public class PostDao implements IPostDao {
	// 컨테이너가 객체를 자동으로 생성 Autowired
	@Autowired
	public SqlSessionTemplate mybatis;
	@Override
	public List<PostDTO> postSelectALL(){
		return mybatis.selectList("postMapper.postSelect");
	}
	public PostDTO postSelectONE(PostDTO dto){
		return mybatis.selectOne("postMapper.postSelect_one",dto);
	}
	
	@Override
	public int postInsert(PostDTO dto) {
		java.util.Date date1 = new java.util.Date();
        java.sql.Timestamp date2 = new java.sql.Timestamp(date1.getTime());
		dto.setPostingDate(date2);
		dto.setPostContents(dto.getPostContents().replaceAll("\r\n", "<br>"));
		return mybatis.insert("postMapper.postInsert",dto);
	}
	@Override
	public int postUpdate(PostDTO dto) {
		dto.setPostContents(dto.getPostContents().replaceAll("\r\n", "<br>"));
		return mybatis.update("postMapper.postUpdate",dto);
	}
	@Override
	public int postDelete(PostDTO dto) {
		return mybatis.delete("postMapper.postDelete",dto);
	}
	
}
