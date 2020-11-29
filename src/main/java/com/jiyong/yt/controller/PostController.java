package com.jiyong.yt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiyong.yt.dto.PostCommentDTO;
import com.jiyong.yt.dto.PostDTO;
import com.jiyong.yt.dto.UserDTO;
import com.jiyong.yt.service.IPostService;
import com.jiyong.yt.util.CustomValidator;
import com.jiyong.yt.util.Paging;

@Controller
public class PostController {
	
	@Autowired
	IPostService postSer;

	@RequestMapping(value = "posting.do")
	public String post_do(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception {
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
			separatorNum = Integer.parseInt(request.getParameter("separatorNum")); //리스트 분할갯수를 리퀘스트에서 받아서 지정해유
		}catch(Exception e) {
			separatorNum = 7;//페이지 분할 갯수가 지정하지 않았을시 7개씩
		}
		try {
			pgseparatorNum = Integer.parseInt(request.getParameter("pgseparatorNum")); //페이지 분할갯수를 리퀘스트에서 받아서 지정해유
		}catch(Exception e) {
			pgseparatorNum = 5;//페이지 분할 갯수가 지정하지 않았을시 5개씩
		}
		List<PostDTO> postdtos= postSer.postSelectALL(); // 쿼리문을 날려서 DB에서 모든 게시판 레코드를 담음  
		Paging.AutoPaging(request, response, model, postdtos, pageNum, separatorNum,pgseparatorNum);
		
		return "posting";
	}
	
	@RequestMapping(value = "postWrite.do")
	public String postWrite_do(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception {
		CustomValidator.getSessionUserData_check(model,request,response);
			
		return "postWrite";
	}
	
	@RequestMapping(value = "postWrite.ing")
	public String postWrite_ing(HttpServletRequest request, HttpServletResponse response, Model model, PostDTO postdto)throws Exception {
		CustomValidator.getSessionUserData_check(model,request,response);
		System.out.println(postdto.getUserId());
		if(postSer.postInsert(postdto)==1) {
			System.out.println("글쓰기 성공");
		}else {
			System.out.println("글쓰기 실패");
		}
		
		
		return "redirect:posting.do";
	}
	
	@RequestMapping(value = "postEdit.do")
	public String postEdit_do(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception {
		CustomValidator.getSessionUserData_check(model,request,response);
		int recodeNum; // 글번호를 담아옵니다.
		try {
			recodeNum = Integer.parseInt(request.getParameter("recodeNum"));
		}catch(Exception e) {
			return "redirect:posting.do"; // 레코드 번호 없이 글수정 접근시 그냥 posting.do로 보냄 
		}
		PostDTO setdto = new PostDTO();
		setdto.setPostId(recodeNum);
		PostDTO postdto = postSer.postSelectONE(setdto);
		postdto.setPostContents(postdto.getPostContents().replaceAll("<br>","\r\n"));
		model.addAttribute("postdto",postdto);
		return "postEdit";
	}
	
	@RequestMapping(value = "postEdit.ing")
	public String postEdit_ing(HttpServletRequest request, HttpServletResponse response, Model model, PostDTO postdto)throws Exception {
		CustomValidator.getSessionUserData_check(model,request,response);
		
		if(postSer.postUpdate(postdto)==1) {
			System.out.println("글수정 성공");
		}else {
			System.out.println("글수정 실패");
		}
		return "redirect:posting.do";
	}
	
	@RequestMapping(value = "postDelete.ing")
	public String postDelete_ing(HttpServletRequest request, HttpServletResponse response, Model model, PostDTO postdto)throws Exception {
		CustomValidator.getSessionUserData_check(model,request,response);
		int recodeNum; // 글번호를 담아옵니다.
		try {
			recodeNum = Integer.parseInt(request.getParameter("recodeNum"));
		}catch(Exception e) {
			return "redirect:posting.do"; // 레코드 번호 없이 글수정 접근시 그냥 posting.do로 보냄 
		}
		postdto.setPostId(recodeNum);
		//아래는 페이지번호 찾기위한 for문
		List<PostDTO> postdtos= postSer.postSelectALL(); // 쿼리문을 날려서 DB에서 모든 게시판 레코드를 담음
		int seperateNum = 5; // 레코드 분할 갯수
		int pageNum = 1;
		for(int i=1;i<postdtos.size();i++) {
			if(postdtos.get(i).getPostId() == recodeNum) {
				pageNum = i/seperateNum+1;
			}
		}
		if(postSer.postDelete(postdto)==1) {
			System.out.println("글삭제 성공");
		}else {
			System.out.println("글삭제 실패");
		}
		
		
		return "redirect:posting.do?pageNum="+pageNum;
	}
	
	@RequestMapping(value = "postView.do")
	public String postView_do(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception {
		CustomValidator.getSessionUserData(model,request,response);
		int recodeNum; // 글번호를 담아옵니다.
		try {
			recodeNum = Integer.parseInt(request.getParameter("recodeNum"));
		}catch(Exception e) {
			return "redirect:posting.do"; // 글 레코드 번호 없이 글보기 접근시 그냥 posting.do로 보냄 
		}
		//공지사항 글 목록 셀렉트부분 시작
		PostDTO setdto = new PostDTO();
		setdto.setPostId(recodeNum);
		PostDTO postdto = postSer.postSelectONE(setdto);
		model.addAttribute("postdto",postdto);
		//아래는 페이지번호 찾기위한 for문
		List<PostDTO> postdtos= postSer.postSelectALL(); // 쿼리문을 날려서 DB에서 모든 게시판 레코드를 담음
		int seperateNum = 5; // 레코드 분할 갯수
		int pageNum = 1;
		for(int i=1;i<postdtos.size();i++) {
			if(postdtos.get(i).getPostId() == recodeNum) {
				pageNum = i/seperateNum+1;
			}
		}
		//공지사항 글 목록 셀렉트부분 끝
		model.addAttribute("pageNum",pageNum);
		//공지사항 댓글목록 셀렉트부분 시작
		PostCommentDTO pcdto = new PostCommentDTO();
		pcdto.setPostId(recodeNum);
		List<PostCommentDTO> postCdtos= postSer.postCommentSelect(pcdto);
		model.addAttribute("postCdtos",postCdtos);
		//공지사항 댓글목록 셀렉트부분 끝
		return "postView";
	}
	
	
	@RequestMapping(value = "postCommentWrite.ing")
	public String postCommentWrite_ing(HttpServletRequest request, HttpServletResponse response, Model model,PostCommentDTO postCdto)throws Exception {
		CustomValidator.getSessionUserData_check(model,request,response);
		UserDTO udto = (UserDTO)request.getSession().getAttribute("userData"); //세션에서 유저정보 가져옴 
		postCdto.setUserId(udto.getUserId());
		postCdto.setCommentDate(new java.sql.Timestamp(new java.util.Date().getTime()));
		if( 1 == postSer.postCommentInsert(postCdto)) {
			System.out.println(postCdto.getUserId() + "님 댓글입력 성공");
		}else {
			System.out.println(postCdto.getUserId() + "님 댓글입력 실패");
		}
		
		
		return "redirect:postView.do?recodeNum="+postCdto.getPostId();
	}
	
	@RequestMapping(value = "postCommentDelete.ing")
	public String postCommentDelete_ing(HttpServletRequest request, HttpServletResponse response, Model model,PostCommentDTO postCdto)throws Exception {
		CustomValidator.getSessionUserData_check(model,request,response);
		UserDTO udto = (UserDTO)request.getSession().getAttribute("userData"); //세션에서 유저정보 가져옴 
		postCdto.setUserId(udto.getUserId());
		if( 1 == postSer.postCommentDelete(postCdto)) {
			System.out.println(postCdto.getCommentId() + "번 댓글 삭제완료");
		}else {
			System.out.println(postCdto.getCommentId() + "번 댓글 삭제실패");
		}
		return "redirect:postView.do?recodeNum="+postCdto.getPostId();
	}
	
	@RequestMapping(value = "postCommentUpdate.ing")
	public String postCommentUpdate_ing(HttpServletRequest request, HttpServletResponse response, Model model,PostCommentDTO postCdto)throws Exception {
		CustomValidator.getSessionUserData_check(model,request,response);
		UserDTO udto = (UserDTO)request.getSession().getAttribute("userData"); //세션에서 유저정보 가져옴 
		postCdto.setUserId(udto.getUserId());
		if( 1 == postSer.postCommentUpdate(postCdto)) {
			System.out.println(postCdto.getCommentId() + "번 댓글 수정완료");
		}else {
			System.out.println(postCdto.getCommentId() + "번 댓글 수정실패");
		}
		return "redirect:postView.do?recodeNum="+postCdto.getPostId();
	}
	
}
