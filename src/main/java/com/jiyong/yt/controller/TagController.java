package com.jiyong.yt.controller;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jiyong.yt.dto.FavorTagDTO;
import com.jiyong.yt.dto.TagDTO;
import com.jiyong.yt.dto.UserDTO;
import com.jiyong.yt.service.ITagService;
import com.jiyong.yt.service.IUserService;
import com.jiyong.yt.util.CustomValidator;
import com.jiyong.yt.util.SHA256;

@Controller
public class TagController {
	
	@Autowired
	ITagService tagSer;
	
	@RequestMapping(value = "tag_my.do")
	public String tag_my_do(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception {
		CustomValidator.getSessionUserData_check(model,request,response);
		FavorTagDTO dto = new FavorTagDTO(); // UserId를 담을 dto
		try {
			dto.setUserId(((UserDTO)request.getSession().getAttribute("userData")).getUserId());
		}catch(Exception e) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이 컨텐츠는 로그인을 필요로 합니다.');location.href='index.do';</script>");
			out.flush();
			return "index";
		}
		List<FavorTagDTO> dtos_group = tagSer.myFavorTag_groupName_Select(dto); 
		List<FavorTagDTO> dtos_all = tagSer.myFavorTag_all_Select(dto); 
		model.addAttribute("dtos_group", dtos_group);
		model.addAttribute("dtos_all", dtos_all);
		
		return "tagConfig";
	}
	// 태그 그룹 넣기
	@RequestMapping(value = "favorGroupInsert.ing")
	public void favorGroupInsert_ing(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception {
		CustomValidator.getSessionUserData_check(model,request,response);
		response.setContentType("text/html; charset=UTF-8");
		
		FavorTagDTO dto = new FavorTagDTO(); // UserId를 담을 dto
		List<String> tagName = new ArrayList<String>(); 
		try {
			dto.setUserId(((UserDTO)request.getSession().getAttribute("userData")).getUserId());
		}catch(Exception e) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이 컨텐츠는 로그인을 필요로 합니다.');location.href='index.do';</script>");
			out.flush();
			return;
		}
		try{
			dto.setGroupName(request.getParameter("groupName"));
			List<FavorTagDTO> dtos_group = tagSer.myFavorTag_groupName_Select(dto);
			int flag=0;
			for(int i=0;i<dtos_group.size();i++) {
				if(dto.getGroupName().equals(dtos_group.get(i).getGroupName())) {
					flag++;
				}
			}
			if(flag>0) {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('이미 존재하는 그룹명입니다.');location.href='tag_my.do';</script>");
				out.flush();
				return;
			}
			tagName.add(request.getParameter("tag1"));
			tagName.add(request.getParameter("tag2"));
			tagName.add(request.getParameter("tag3"));
		}catch(Exception e) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('입력된 부분에 오류가 있습니다.');location.href='tag_my.do';</script>");
			out.flush();
			return;
		}
		
		
		for(int i=0;i<tagName.size();i++) {
			dto.setTagName(tagName.get(i));
			tagSer.myFavorTag_Insert(dto);
		}
		
		PrintWriter out = response.getWriter();
		out.println("<script>location.href='tag_my.do';</script>");
		out.flush();
	}
	
	
	@RequestMapping(value = "favorGroupDelete.ing")
	public void favorGroupDelete_ing(HttpServletRequest request, HttpServletResponse response, Model model,FavorTagDTO dto)throws Exception {
		CustomValidator.getSessionUserData_check(model,request,response);
		try {
		dto.setUserId(((UserDTO)request.getSession().getAttribute("userData")).getUserId());
		}catch(Exception e){
			
		}
		if(tagSer.myFavorTag_Group_Delete(dto)==1){
			System.out.println("그룹삭제성공");
		}else {
			System.out.println("그룹삭제실패");
		}
		
		PrintWriter out = response.getWriter();
		out.println("<script>location.href='tag_my.do';</script>");
		out.flush();
		
	}
	
	@RequestMapping(value = "favorTagDelete.ing")
	public void favorTagDelete_ing(HttpServletRequest request, HttpServletResponse response, Model model,FavorTagDTO dto)throws Exception {
		CustomValidator.getSessionUserData_check(model,request,response);
		try {
		dto.setUserId(((UserDTO)request.getSession().getAttribute("userData")).getUserId());
		}catch(Exception e){
			
		}
		if(tagSer.myFavorTag_Delete(dto)==1){
			System.out.println("태그삭제성공");
		}else {
			System.out.println("태그삭제실패");
		}
		
		PrintWriter out = response.getWriter();
		out.println("<script>location.href='tag_my.do';</script>");
		out.flush();
		
	}
	
	@RequestMapping(value = "favorTagInsert.ing")
	public void favorTagInsert_ing(HttpServletRequest request, HttpServletResponse response, Model model,FavorTagDTO dto)throws Exception {
		CustomValidator.getSessionUserData_check(model,request,response);
		try {
		dto.setUserId(((UserDTO)request.getSession().getAttribute("userData")).getUserId());
		}catch(Exception e){
			
		}
		if(tagSer.myFavorTag_Insert(dto)==1){
			System.out.println("태그입력성공");
		}else {
			System.out.println("태그입력실패");
		}
		
		PrintWriter out = response.getWriter();
		out.println("<script>location.href='tag_my.do';</script>");
		out.flush();
		
	}
}
