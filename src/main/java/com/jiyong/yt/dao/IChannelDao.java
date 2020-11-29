package com.jiyong.yt.dao;

import java.util.List;

import com.jiyong.yt.dto.ChannelDTO;
import com.jiyong.yt.dto.FavorTagDTO;
import com.jiyong.yt.dto.LikeDTO;
import com.jiyong.yt.dto.TagDTO;

// [DB연결 사용법] 2. Dao인터페이스 생성
public interface IChannelDao {
	public List<ChannelDTO> channelSelectALL();
	public List<ChannelDTO> channelSelectALL2();
	public ChannelDTO channelSelectONE(ChannelDTO dto);
	public int channelInsert (ChannelDTO dto);	// 사용할 추상메소드 정의
	public int channelUpdate(ChannelDTO dto);
	public int channelDelete(ChannelDTO dto);
	
	//일반채널
	public int likeInsert(LikeDTO dto);
	public int tagInsert(TagDTO dto);
	
	//////선호채널
	public List<ChannelDTO> channelSelectALL_my(FavorTagDTO dto);
	///좋아요
	public int likeInsert_my(LikeDTO dto);
	//태그
	public int tagInsert_my(TagDTO dto);
}
