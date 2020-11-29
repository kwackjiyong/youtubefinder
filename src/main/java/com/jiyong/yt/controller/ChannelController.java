package com.jiyong.yt.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiyong.yt.dto.ChannelDTO;
import com.jiyong.yt.dto.FavorTagDTO;
import com.jiyong.yt.dto.LikeDTO;
import com.jiyong.yt.dto.PostDTO;
import com.jiyong.yt.dto.TagDTO;
import com.jiyong.yt.dto.UserDTO;
import com.jiyong.yt.service.IChannelService;
import com.jiyong.yt.service.IPostService;
import com.jiyong.yt.util.CustomValidator;
import com.jiyong.yt.util.Paging;

@Controller
public class ChannelController {
	@Autowired
	IChannelService chnSer;
	//새로나온 채널순으로 바로 보기
	@RequestMapping(value = "channel.do")
	public String channel_new_do(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception {
		CustomValidator.getSessionUserData(model,request,response);
		int pageNum;
		int separatorNum;
		int pgseparatorNum;
		try {
			pageNum = Integer.parseInt(request.getParameter("pageNum")); // 페이지 넘버를 리퀘스트에서 받아서 지정해유
		}catch(Exception e) {
			pageNum = 1; //페이지넘버가 지정하지않았을시 1페이지루 가 
		}
		try {
			separatorNum = Integer.parseInt(request.getParameter("separatorNum")); //분할갯수를 리퀘스트에서 받아서 지정해유
		}catch(Exception e) {
			separatorNum = 9;//페이지 분할 갯수가 지정하지 않았을시 9개씩
		}
		try {
			pgseparatorNum = Integer.parseInt(request.getParameter("pgseparatorNum")); //분할갯수를 리퀘스트에서 받아서 지정해유
		}catch(Exception e) {
			pgseparatorNum = 5;//페이지 분할 갯수가 지정하지 않았을시 5개씩
		}
		List<ChannelDTO> list= chnSer.channelSelectALL2(); // 쿼리문을 날려서 DB에서 모든 게시판 레코드를 담음
		
		Paging.AutoPaging(request, response, model, list, pageNum, separatorNum,pgseparatorNum); //모델에 페이징된 리스트와 페이지번호리스트를 자동셋팅해주는 메서드
		
		
		/*
		ArrayList<ArrayList<PostDTO>> seperated_chndtos =(ArrayList<ArrayList<PostDTO>>) Paging.paging_list(chndtos, separatorNum); // 7개씩보기로 7개씩 페이지를 분할하여 ArrayList에 담음
		ArrayList<ArrayList<Integer>> pageNum_list = Paging.pageNum_list(chndtos, separatorNum);// 7개씩보기에 맞춰 생성된 페이지번호를 5개씩 잘라서 ArrayList로 보관합니다.
		int pagelistNum = Paging.pageNum_ListNum(pageNum_list, pageNum); // 위에서 작성된 페이지번호리스트의 5개씩 그룹된 그룹번호입니다.
		int lastPageNum = Paging.lastPaging(chndtos, pageNum_list,separatorNum);//마지막번호를 찾아줍니다.
		System.out.println("마지막페이지 : "+lastPageNum);
		System.out.println("현재페이지 : "+pageNum);
		model.addAttribute("chndtos", seperated_chndtos.get(pageNum-1));//페이지별로 분리된 레코드의 리스트를 넣어줍니다.(5개씩잘랐다면 최대 5개)
		model.addAttribute("chnPageNum",pageNum); // 페이지넘버를 넣어줍니다.
		model.addAttribute("chnPageNumList_lastNum",lastPageNum);//마지막번호를 넣어줍니다.
		model.addAttribute("chnPageNumList",pageNum_list.get(pagelistNum)); // 페이지번호리스트의 그룹번호를 페이지번호리스트에 넣어 해당 페이지번호리스트를 가져옵니다.
		*/
		return "channel";
	}
	
	@RequestMapping(value = "channelLike.ing")
	public void channel_Like_ing(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception {
		CustomValidator.getSessionUserData_check(model,request,response);
		
		int channelId=0;
		int like_whether=0;
		try {
			channelId = Integer.parseInt(request.getParameter("channelId"));
		}catch(Exception e){
			
		}
		try {
			like_whether= Integer.parseInt(request.getParameter("like_whether"));
		}catch(Exception e){
			
		}
		LikeDTO dto = new LikeDTO();
		dto.setChannelId(channelId);
		dto.setLikeWhether(like_whether);
		try {
		dto.setUserId(((UserDTO)request.getSession().getAttribute("userData")).getUserId());
		}catch(Exception e) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이 컨텐츠는 로그인을 필요로 합니다.');location.href='index.do';</script>");
			out.flush();
			return;
		}
		//자기 페이지 찾아감
		List<ChannelDTO> chndtos= chnSer.channelSelectALL2(); // 쿼리문을 날려서 DB에서 모든 게시판 레코드를 담음
		int seperateNum = 9; // 레코드 분할 갯수
		int pageNum = 1;
		for(int i=1;i<chndtos.size();i++) {
			if(chndtos.get(i).getChannelId() == channelId) {
				pageNum = i/seperateNum+1;
			}
		}
		if(chnSer.likeInsert(dto) == 1) {
			System.out.println("조아요 성공");
		}else {
			System.out.println("조아요 실패");
		}
		
		PrintWriter out = response.getWriter();
		out.println("<script>location.href='channel.do?pageNum="+pageNum+"';</script>");
		out.flush();
		//return"redirect:channel.do?pageNum="+pageNum;
	}
	
	@RequestMapping(value = "channelTag.ing")
	public void channel_Tag_ing(HttpServletRequest request, HttpServletResponse response, Model model,TagDTO dto)throws Exception {
		CustomValidator.getSessionUserData_check(model,request,response);
		try {
		dto.setUserId(((UserDTO)request.getSession().getAttribute("userData")).getUserId());
		}catch(Exception e){
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='channel.do';</script>");
			out.flush();
			return;
		}
		if(dto.getTagName().equals("")) {
			System.out.println("빈칸");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='channel.do';</script>");
			out.flush();
			return;
		}
		//자기 페이지 찾아감
				List<ChannelDTO> chndtos= chnSer.channelSelectALL2(); // 쿼리문을 날려서 DB에서 모든 게시판 레코드를 담음
				int seperateNum = 9; // 레코드 분할 갯수
				int pageNum = 1;
				for(int i=1;i<chndtos.size();i++) {
					if(chndtos.get(i).getChannelId() == dto.getChannelId()) {
						pageNum = i/seperateNum+1;
					}
				}
		if(chnSer.tagInsert(dto) == 1) {
			System.out.println("태그 성공");
		}else {
			System.out.println("태그 실패");
		}
		
		PrintWriter out = response.getWriter();
		out.println("<script>location.href='channel.do?pageNum="+pageNum+"';</script>");
		out.flush();
	
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////여기부턴 선호채널보기 관련 ////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
	
	@RequestMapping(value = "channel_my.do")
	public String channel_my_do(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception {
		CustomValidator.getSessionUserData_check(model,request,response);
		UserDTO userdto = new UserDTO();
		FavorTagDTO ftdto = new FavorTagDTO();
		try {
			userdto = (UserDTO)request.getSession().getAttribute("userData");
			ftdto.setGroupName(userdto.getFavorTagGroup());
			ftdto.setUserId(userdto.getUserId());
		}catch(Exception e){
		}
		int pageNum;
		int separatorNum;
		int pgseparatorNum;
		try {
			pageNum = Integer.parseInt(request.getParameter("pageNum")); // 페이지 넘버를 리퀘스트에서 받아서 지정해유
		}catch(Exception e) {
			pageNum = 1; //페이지넘버가 지정하지않았을시 1페이지루 가 
		}
		try {
			separatorNum = Integer.parseInt(request.getParameter("separatorNum")); //분할갯수를 리퀘스트에서 받아서 지정해유
		}catch(Exception e) {
			separatorNum = 9;//페이지 분할 갯수가 지정하지 않았을시 9개씩
		}
		try {
			pgseparatorNum = Integer.parseInt(request.getParameter("pgseparatorNum")); //분할갯수를 리퀘스트에서 받아서 지정해유
		}catch(Exception e) {
			pgseparatorNum = 5;//페이지 분할 갯수가 지정하지 않았을시 5개씩
		}
		List<ChannelDTO> list= chnSer.channelSelectALL_my(ftdto); // 쿼리문을 날려서 DB에서 모든 게시판 레코드를 담음
		//하나도 없을 때
		if(list.size() == 0) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('관련된 채널이 존재하지 않습니다.');location.href='index.do';</script>");
			out.flush();
			return "index";
		}
		
		Paging.AutoPaging(request, response, model, list, pageNum, separatorNum,pgseparatorNum); //모델에 페이징된 리스트와 페이지번호리스트를 자동셋팅해주는 메서드
		
		//
		/*
		ArrayList<ArrayList<PostDTO>> seperated_chndtos =(ArrayList<ArrayList<PostDTO>>) Paging.paging_list(chndtos, separatorNum); // 7개씩보기로 7개씩 페이지를 분할하여 ArrayList에 담음
		ArrayList<ArrayList<Integer>> pageNum_list = Paging.pageNum_list(chndtos, separatorNum);// 7개씩보기에 맞춰 생성된 페이지번호를 5개씩 잘라서 ArrayList로 보관합니다.
		int pagelistNum = Paging.pageNum_ListNum(pageNum_list, pageNum); // 위에서 작성된 페이지번호리스트의 5개씩 그룹된 그룹번호입니다.
		int lastPageNum = Paging.lastPaging(chndtos, pageNum_list,separatorNum);//마지막번호를 찾아줍니다.
		System.out.println("마지막페이지 : "+lastPageNum);
		System.out.println("현재페이지 : "+pageNum);
		model.addAttribute("chndtos", seperated_chndtos.get(pageNum-1));//페이지별로 분리된 레코드의 리스트를 넣어줍니다.(5개씩잘랐다면 최대 5개)
		model.addAttribute("chnPageNum",pageNum); // 페이지넘버를 넣어줍니다.
		model.addAttribute("chnPageNumList_lastNum",lastPageNum);//마지막번호를 넣어줍니다.
		model.addAttribute("chnPageNumList",pageNum_list.get(pagelistNum)); // 페이지번호리스트의 그룹번호를 페이지번호리스트에 넣어 해당 페이지번호리스트를 가져옵니다.
		*/
		return "channel_my";
	}
	
	@RequestMapping(value = "channelLike_my.ing")
	public void channel_my_Like_ing(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception {
		CustomValidator.getSessionUserData_check(model,request,response);
		
		int channelId=0;
		int like_whether=0;
		try {
			channelId = Integer.parseInt(request.getParameter("channelId"));
		}catch(Exception e){
			
		}
		try {
			like_whether= Integer.parseInt(request.getParameter("like_whether"));
		}catch(Exception e){
			
		}
		LikeDTO dto = new LikeDTO();
		dto.setChannelId(channelId);
		dto.setLikeWhether(like_whether);
		try {
		dto.setUserId(((UserDTO)request.getSession().getAttribute("userData")).getUserId());
		}catch(Exception e) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이 컨텐츠는 로그인을 필요로 합니다.');location.href='index.do';</script>");
			out.flush();
			return;
		}
		//자기 페이지 찾아감
		UserDTO userdto = new UserDTO();
		FavorTagDTO ftdto = new FavorTagDTO();
		try {
			userdto = (UserDTO)request.getSession().getAttribute("userData");
			ftdto.setGroupName(userdto.getFavorTagGroup());
			ftdto.setUserId(userdto.getUserId());
		}catch(Exception e){
		}
		List<ChannelDTO> chndtos= chnSer.channelSelectALL_my(ftdto); // 쿼리문을 날려서 DB에서 모든 게시판 레코드를 담음
		int seperateNum = 9; // 레코드 분할 갯수
		int pageNum = 1;
		for(int i=1;i<chndtos.size();i++) {
			if(chndtos.get(i).getChannelId() == channelId) {
				pageNum = i/seperateNum+1;
			}
		}
		if(chnSer.likeInsert_my(dto) == 1) {
			System.out.println("조아요 성공");
		}else {
			System.out.println("조아요 실패");
		}
		
		PrintWriter out = response.getWriter();
		out.println("<script>location.href='channel_my.do?pageNum="+pageNum+"';</script>");
		out.flush();
		//return"redirect:channel.do?pageNum="+pageNum;
	}
	
	@RequestMapping(value = "channelTag_my.ing")
	public void channel_my_Tag_ing(HttpServletRequest request, HttpServletResponse response, Model model,TagDTO dto)throws Exception {
		CustomValidator.getSessionUserData_check(model,request,response);
		
		try {
		dto.setUserId(((UserDTO)request.getSession().getAttribute("userData")).getUserId());
		}catch(Exception e){
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='channel_my.do';</script>");
			out.flush();
			return;
		}
		if(dto.getTagName().equals("")) {
			System.out.println("빈칸");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='channel_my.do';</script>");
			out.flush();
			return;
		}
		
		
		//자기 페이지 찾아감
		
		UserDTO userdto = new UserDTO();
		FavorTagDTO ftdto = new FavorTagDTO();
		try {
			userdto = (UserDTO)request.getSession().getAttribute("userData");
			ftdto.setGroupName(userdto.getFavorTagGroup());
			ftdto.setUserId(userdto.getUserId());
		}catch(Exception e){
		}
				List<ChannelDTO> chndtos= chnSer.channelSelectALL_my(ftdto); // 쿼리문을 날려서 DB에서 모든 게시판 레코드를 담음
				int seperateNum = 9; // 레코드 분할 갯수
				int pageNum = 1;
				for(int i=1;i<chndtos.size();i++) {
					if(chndtos.get(i).getChannelId() == dto.getChannelId()) {
						pageNum = i/seperateNum+1;
					}
				}
		if(chnSer.tagInsert_my(dto) == 1) {
			System.out.println("태그 성공");
		}else {
			System.out.println("태그 실패");
		}
		
		PrintWriter out = response.getWriter();
		out.println("<script>location.href='channel_my.do?pageNum="+pageNum+"';</script>");
		out.flush();
	
	}
	
}
