package com.jiyong.yt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.jiyong.yt.dao.ITagDao;
import com.jiyong.yt.dto.FavorTagDTO;
import com.jiyong.yt.dto.TagDTO;
//[DB연결 사용법] 6. 서비스 클래스 작성
@Service("ITagService")
public class TagService implements ITagService {
	// 인터페이스로 생성해야함
	@Autowired
	public ITagDao dao;
	
	@Override
	public int tagInsert(TagDTO dto) {
		return dao.tagInsert(dto);
	}
	@Override
	public List<TagDTO> tagSelectMy(TagDTO dto){
		return dao.tagSelectMy(dto);
	}
	@Override
	public List<TagDTO> tagSelectALL() {
		return dao.tagSelectALL();
	}
	@Override
	public List<TagDTO> tagSelectWeek(TagDTO dto) {
		return dao.tagSelectWeek(dto);
	}
	@Override
	public List<TagDTO> tagSelectNew(TagDTO dto) {
		return dao.tagSelectNew(dto);
	}
	
	@Override
	public List<TagDTO> tagSelectName(){
		return dao.tagSelectName();
	}
	@Override
	public List<Integer> tagSelectCount(){
		return dao.tagSelectCount();
	}
	
	@Override
	public List<TagDTO> tagSelectWeekName(TagDTO dto){
		return dao.tagSelectWeekName(dto);
	}
	@Override
	public List<Integer> tagSelectWeekCount(TagDTO dto){
		return dao.tagSelectWeekCount(dto);
	}
	
	// 선호 태그 설정
	@Override
	public List<FavorTagDTO> myFavorTag_groupName_Select(FavorTagDTO dto){
		return dao.myFavorTag_groupName_Select(dto);
	}
	@Override
	public List<FavorTagDTO> myFavorTag_all_Select(FavorTagDTO dto){
		return dao.myFavorTag_all_Select(dto);
	}
	@Override
	public int myFavorTag_Insert(FavorTagDTO dto) {
		return dao.myFavorTag_Insert(dto);
	}
	@Override
	public int myFavorTag_Group_Delete(FavorTagDTO dto) {
		return dao.myFavorTag_Group_Delete(dto);
	}
	@Override
	public int myFavorTag_Delete(FavorTagDTO dto) {
		return dao.myFavorTag_Delete(dto);
	}
}
