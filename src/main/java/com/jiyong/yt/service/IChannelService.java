package com.jiyong.yt.service;

import java.util.List;

import com.jiyong.yt.dto.ChannelDTO;
import com.jiyong.yt.dto.FavorTagDTO;
import com.jiyong.yt.dto.LikeDTO;
import com.jiyong.yt.dto.PostCommentDTO;
import com.jiyong.yt.dto.PostDTO;
import com.jiyong.yt.dto.TagDTO;

// [DB연결 사용법] 5. 서비스 인터페이스 작성
public interface IChannelService {
	public List<ChannelDTO> channelSelectALL();
	public List<ChannelDTO> channelSelectALL2();
	public ChannelDTO channelSelectONE(ChannelDTO dto);
	public int channelInsert(ChannelDTO dto);	// 사용할 추상메소드 정의
	public int channelUpdate(ChannelDTO dto);
	public int channelDelete(ChannelDTO dto);
	
	///좋아요
	public int likeInsert(LikeDTO dto);
	//태그
	public int tagInsert(TagDTO dto);
	
	
	///////////////선호채널 관련
	public List<ChannelDTO> channelSelectALL_my(FavorTagDTO dto);
	///좋아요
	public int likeInsert_my(LikeDTO dto);
	//태그
	public int tagInsert_my(TagDTO dto);
	
}
