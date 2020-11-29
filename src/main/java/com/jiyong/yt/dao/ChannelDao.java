package com.jiyong.yt.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jiyong.yt.dto.ChannelDTO;
import com.jiyong.yt.dto.FavorTagDTO;
import com.jiyong.yt.dto.LikeDTO;
import com.jiyong.yt.dto.TagDTO;
//[DB연결 사용법] 3. 인터페이스 받아서 클래스 생성
@Repository
public class ChannelDao implements IChannelDao {
	// 컨테이너가 객체를 자동으로 생성 Autowired
	@Autowired
	public SqlSessionTemplate mybatis;
	@Override
	public List<ChannelDTO> channelSelectALL(){
		return mybatis.selectList("channelMapper.channelSelect");
	}
	@Override
	public ChannelDTO channelSelectONE(ChannelDTO dto){
		return mybatis.selectOne("channelMapper.channelSelect_one",dto);
	}
	
	
	
	@Override
	public int channelInsert(ChannelDTO dto) {
		java.util.Date date1 = new java.util.Date();
        java.sql.Timestamp date2 = new java.sql.Timestamp(date1.getTime());
		dto.setRegisterDate(date2);
		dto.setChannelExp(dto.getChannelExp().replaceAll("\r\n", "<br>"));
		return mybatis.insert("channelMapper.channelInsert",dto);
	}
	@Override
	public int channelUpdate(ChannelDTO dto) {
		dto.setChannelExp(dto.getChannelExp().replaceAll("\r\n", "<br>"));
		return mybatis.update("channelMapper.channelUpdate",dto);
	}
	@Override
	public int channelDelete(ChannelDTO dto) {
		return mybatis.delete("channelMapper.channelDelete",dto);
	}
	
	@Override
	public List<ChannelDTO> channelSelectALL2(){
		return mybatis.selectList("channelMapper.channelSelect2");
	}
	///좋아요
		@Override
		public int likeInsert(LikeDTO dto) {
			java.util.Date date1 = new java.util.Date();
	        java.sql.Timestamp date2 = new java.sql.Timestamp(date1.getTime());
			dto.setLikeDate(date2);
			return mybatis.insert("channelMapper.likeInsert",dto);
		}
		//태그
		@Override
		public int tagInsert(TagDTO dto) {
			java.util.Date date1 = new java.util.Date();
	        java.sql.Timestamp date2 = new java.sql.Timestamp(date1.getTime());
			dto.setTaggingDate(date2);
			return mybatis.insert("channelMapper.tagInsert",dto);
			
		}
		
	//////////////////////////////////////////////// 선호채널
		@Override
		public List<ChannelDTO> channelSelectALL_my(FavorTagDTO dto){
			return mybatis.selectList("channelMapper.channelSelect_my",dto);
		}
		///좋아요
			@Override
			public int likeInsert_my(LikeDTO dto) {
				java.util.Date date1 = new java.util.Date();
		        java.sql.Timestamp date2 = new java.sql.Timestamp(date1.getTime());
				dto.setLikeDate(date2);
				return mybatis.insert("channelMapper.likeInsert_my",dto);
			}
			//태그
			@Override
			public int tagInsert_my(TagDTO dto) {
				java.util.Date date1 = new java.util.Date();
		        java.sql.Timestamp date2 = new java.sql.Timestamp(date1.getTime());
				dto.setTaggingDate(date2);
				return mybatis.insert("channelMapper.tagInsert_my",dto);
				
			}	
	
}
