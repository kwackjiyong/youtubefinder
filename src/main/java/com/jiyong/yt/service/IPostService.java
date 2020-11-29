package com.jiyong.yt.service;

import java.util.List;

import com.jiyong.yt.dto.PostCommentDTO;
import com.jiyong.yt.dto.PostDTO;
import com.jiyong.yt.dto.TagDTO;

// [DB연결 사용법] 5. 서비스 인터페이스 작성
public interface IPostService {
	public List<PostDTO> postSelectALL();
	public PostDTO postSelectONE(PostDTO dto);
	public int postInsert(PostDTO dto);	// 사용할 추상메소드 정의
	public int postUpdate(PostDTO dto);
	public int postDelete(PostDTO dto);
	//////////////////////////////////////////////////////////////////////////////////
	public List<PostCommentDTO> postCommentSelect(PostCommentDTO dto);
	public PostCommentDTO postCommentSelectONE(PostCommentDTO dto);
	public int postCommentInsert (PostCommentDTO dto);	// 사용할 추상메소드 정의
	public int postCommentUpdate(PostCommentDTO dto);
	public int postCommentDelete(PostCommentDTO dto);
	
}
