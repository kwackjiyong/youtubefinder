package com.jiyong.yt.service;

import java.util.List;

import com.jiyong.yt.dto.FavorTagDTO;
import com.jiyong.yt.dto.TagDTO;

// [DB연결 사용법] 5. 서비스 인터페이스 작성
public interface ITagService {
	public int tagInsert(TagDTO dto);
	public List<TagDTO> tagSelectMy(TagDTO dto);
	public List<TagDTO> tagSelectALL();
	public List<TagDTO> tagSelectWeek(TagDTO dto);
	public List<TagDTO> tagSelectNew(TagDTO dto);
	public List<TagDTO> tagSelectName();
	public List<Integer> tagSelectCount();
	public List<TagDTO> tagSelectWeekName(TagDTO dto);
	public List<Integer> tagSelectWeekCount(TagDTO dto);
	
	
	//선호태그 설정
	public List<FavorTagDTO> myFavorTag_groupName_Select(FavorTagDTO dto);
	public List<FavorTagDTO> myFavorTag_all_Select(FavorTagDTO dto);
	public int myFavorTag_Insert(FavorTagDTO dto);
	public int myFavorTag_Group_Delete(FavorTagDTO dto);
	public int myFavorTag_Delete(FavorTagDTO dto);
}
