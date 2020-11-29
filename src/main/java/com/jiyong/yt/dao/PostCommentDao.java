package com.jiyong.yt.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jiyong.yt.dto.PostCommentDTO;

//[DB연결 사용법] 3. 인터페이스 받아서 클래스 생성
@Repository
public class PostCommentDao implements IPostCommentDao {
	
	@Autowired
	public SqlSessionTemplate mybatis;
	@Override
	public List<PostCommentDTO> postCommentSelect(PostCommentDTO dto){
		return mybatis.selectList("postMapper.postCommentSelect",dto);
	}
	public PostCommentDTO postCommentSelectONE(PostCommentDTO dto){
		return mybatis.selectOne("postMapper.postSelect_one",dto);
	}
	
	@Override
	public int postCommentInsert(PostCommentDTO dto) {
		java.util.Date date1 = new java.util.Date();
        java.sql.Timestamp date2 = new java.sql.Timestamp(date1.getTime());
		dto.setCommentDate(date2);
		dto.setContents(dto.getContents().replaceAll("\r\n", "<br>"));
		return mybatis.insert("postMapper.postCommentInsert",dto);
	}
	@Override
	public int postCommentUpdate(PostCommentDTO dto) {
		dto.setContents(dto.getContents().replaceAll("\r\n", "<br>"));
		return mybatis.update("postMapper.postCommentUpdate",dto);
	}
	@Override
	public int postCommentDelete(PostCommentDTO dto) {
		return mybatis.delete("postMapper.postCommentDelete",dto);
	}
	
}
