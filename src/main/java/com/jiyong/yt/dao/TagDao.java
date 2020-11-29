package com.jiyong.yt.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jiyong.yt.dto.FavorTagDTO;
import com.jiyong.yt.dto.TagDTO;
//[DB연결 사용법] 3. 인터페이스 받아서 클래스 생성
@Repository
public class TagDao implements ITagDao {
	// 컨테이너가 객체를 자동으로 생성 Autowired
	@Autowired
	public SqlSessionTemplate mybatis;
	
	@Override
	public int tagInsert(TagDTO dto) {
		// TODO Auto-generated method stub
		
		return mybatis.insert("tagMapper.tagInsert", dto);
	}
	
	@Override
	public List<TagDTO> tagSelectMy(TagDTO dto) {
		// TODO Auto-generated method stub
		return mybatis.selectList("tagMapper.tagSelectMy",dto);
	}
	
	@Override
	public List<TagDTO> tagSelectALL() {
		// TODO Auto-generated method stub
		return mybatis.selectList("tagMapper.tagSelectALL");
	}
	@Override
	public List<TagDTO> tagSelectName() {
		// TODO Auto-generated method stub
		return mybatis.selectList("tagMapper.tagSelectName");
	}
	@Override
	public List<Integer> tagSelectCount() {
		// TODO Auto-generated method stub
		return mybatis.selectList("tagMapper.tagSelectCount");
	}
	
	@Override
	public List<TagDTO> tagSelectWeekName(TagDTO dto) {
		// TODO Auto-generated method stub
		return mybatis.selectList("tagMapper.tagSelectWeekName",dto);
	}
	@Override
	public List<Integer> tagSelectWeekCount(TagDTO dto) {
		// TODO Auto-generated method stub
		return mybatis.selectList("tagMapper.tagSelectWeekCount",dto);
	}
	
	@Override
	public List<TagDTO> tagSelectWeek(TagDTO dto) {
		// TODO Auto-generated method stub
		return mybatis.selectList("tagMapper.tagSelectWeek", dto);
	}
	@Override
	public List<TagDTO> tagSelectNew(TagDTO dto) {
		// TODO Auto-generated method stub
		return mybatis.selectList("tagMapper.tagSelectNew", dto);
	}
	@Override
	public List<FavorTagDTO> myFavorTag_groupName_Select(FavorTagDTO dto){
		return mybatis.selectList("tagMapper.myFavorTag_groupName_Select", dto);
	}
	@Override
	public List<FavorTagDTO> myFavorTag_all_Select(FavorTagDTO dto){
		return mybatis.selectList("tagMapper.myFavorTag_all_Select", dto);
	}
	@Override
	public int myFavorTag_Insert(FavorTagDTO dto) {
		return mybatis.insert("tagMapper.myFavorTag_Insert", dto);
	}
	@Override
	public int myFavorTag_Group_Delete(FavorTagDTO dto) {
		return mybatis.delete("tagMapper.myFavorTag_Group_Delete", dto);
	}
	@Override
	public int myFavorTag_Delete(FavorTagDTO dto) {
		return mybatis.delete("tagMapper.myFavorTag_Delete", dto);
	}
}
