package com.jiyong.yt.dao;

import java.util.List;

import com.jiyong.yt.dto.PostDTO;
import com.jiyong.yt.dto.TagDTO;

// [DB연결 사용법] 2. Dao인터페이스 생성
public interface IPostDao {
	public List<PostDTO> postSelectALL();
	public PostDTO postSelectONE(PostDTO dto);
	public int postInsert (PostDTO dto);	// 사용할 추상메소드 정의
	public int postUpdate(PostDTO dto);
	public int postDelete(PostDTO dto);
}
