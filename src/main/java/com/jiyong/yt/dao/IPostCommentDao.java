package com.jiyong.yt.dao;

import java.util.List;

import com.jiyong.yt.dto.PostCommentDTO;
import com.jiyong.yt.dto.PostDTO;
import com.jiyong.yt.dto.TagDTO;

// [DB연결 사용법] 2. Dao인터페이스 생성
public interface IPostCommentDao {
	public List<PostCommentDTO> postCommentSelect(PostCommentDTO dto);
	public PostCommentDTO postCommentSelectONE(PostCommentDTO dto);
	public int postCommentInsert (PostCommentDTO dto);	// 사용할 추상메소드 정의
	public int postCommentUpdate(PostCommentDTO dto);
	public int postCommentDelete(PostCommentDTO dto);
}
