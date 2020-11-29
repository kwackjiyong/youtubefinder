package com.jiyong.yt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.jiyong.yt.dao.IChannelDao;
import com.jiyong.yt.dao.ITagDao;
import com.jiyong.yt.dto.ChannelDTO;
import com.jiyong.yt.dto.FavorTagDTO;
import com.jiyong.yt.dto.LikeDTO;
import com.jiyong.yt.dto.TagDTO;
//[DB연결 사용법] 6. 서비스 클래스 작성
@Service("IChannelService")
public class ChannelService implements IChannelService {
	// 인터페이스로 생성해야함
	@Autowired
	public IChannelDao dao;

	@Override
	public List<ChannelDTO> channelSelectALL(){
		return dao.channelSelectALL();
	}
	@Override
	public List<ChannelDTO> channelSelectALL2(){
		return dao.channelSelectALL2();
	}
	@Override
	public ChannelDTO channelSelectONE(ChannelDTO dto) {
		return dao.channelSelectONE(dto);
	}
	@Override
	public int channelInsert (ChannelDTO dto) {
		return dao.channelInsert(dto);
	};	
	@Override
	public int channelUpdate(ChannelDTO dto) {
		return dao.channelUpdate(dto);
	}
	@Override
	public int channelDelete(ChannelDTO dto) {
		return dao.channelDelete(dto);
	}
	
	///좋아요
	@Override
	public int likeInsert(LikeDTO dto) {
		return dao.likeInsert(dto);
	}
	//태그
	@Override
	public int tagInsert(TagDTO dto) {
		return dao.tagInsert(dto);
	}
	
	/////////////////////////////////////선호채널
	@Override
	public List<ChannelDTO> channelSelectALL_my(FavorTagDTO dto){
		return dao.channelSelectALL_my(dto);
	}
	///좋아요
		@Override
		public int likeInsert_my(LikeDTO dto) {
			return dao.likeInsert_my(dto);
		}
		//태그
		@Override
		public int tagInsert_my(TagDTO dto) {
			return dao.tagInsert_my(dto);
		}	
	
}
