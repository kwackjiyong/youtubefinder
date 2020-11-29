package com.jiyong.yt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.jiyong.yt.dao.IPostCommentDao;
import com.jiyong.yt.dao.IPostDao;
import com.jiyong.yt.dao.ITagDao;
import com.jiyong.yt.dto.PostCommentDTO;
import com.jiyong.yt.dto.PostDTO;
import com.jiyong.yt.dto.TagDTO;
//[DB연결 사용법] 6. 서비스 클래스 작성
@Service("IPostService")
public class PostService implements IPostService {
	// 인터페이스로 생성해야함
	@Autowired
	public IPostDao dao;

	@Override
	public List<PostDTO> postSelectALL(){
		return dao.postSelectALL();
	}
	@Override
	public PostDTO postSelectONE(PostDTO dto) {
		return dao.postSelectONE(dto);
	}
	@Override
	public int postInsert (PostDTO dto) {
		return dao.postInsert(dto);
	};	
	@Override
	public int postUpdate(PostDTO dto) {
		return dao.postUpdate(dto);
	}
	@Override
	public int postDelete(PostDTO dto) {
		return dao.postDelete(dto);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	@Autowired
	public IPostCommentDao dao2;
	
	public List<PostCommentDTO> postCommentSelect(PostCommentDTO dto){
		return dao2.postCommentSelect(dto);
	}
	public PostCommentDTO postCommentSelectONE(PostCommentDTO dto){
		return dao2.postCommentSelectONE(dto);
	}
	
	@Override
	public int postCommentInsert(PostCommentDTO dto) {
		return dao2.postCommentInsert(dto);
	}
	@Override
	public int postCommentUpdate(PostCommentDTO dto) {
		return dao2.postCommentUpdate(dto);
	}
	@Override
	public int postCommentDelete(PostCommentDTO dto) {
		return dao2.postCommentDelete(dto);
	}
}
