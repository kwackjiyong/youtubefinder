package com.jiyong.yt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jiyong.yt.dto.PostDTO;
import com.jiyong.yt.service.IPostService;
import com.jiyong.yt.util.Paging;

@Controller
public class PagingExamController {
	
	@Autowired
	IPostService postSer;
	
	
	@RequestMapping(value = "/pagingExam.do" ,method = RequestMethod.GET)
	public String pagingExam(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception {
		int pageNum; // 현재 페이지 번호
		int separatorNum; // ? 개씩 보기를  정하는 숫자  / 분할갯수
		int pgseparatorNum; // 페이지 리스트 분할갯수 
		try {
			pageNum = Integer.parseInt(request.getParameter("pageNum")); // 페이지 넘버를 리퀘스트에서 받아서 지정해유
		}catch(Exception e) {
			pageNum = 1; //페이지넘버가 지정하지않았을시 1페이지루 가 
		}
		try {
			separatorNum = Integer.parseInt(request.getParameter("separatorNum")); //분할갯수를 리퀘스트에서 받아서 지정해유
		}catch(Exception e) {
			separatorNum = 5;//페이지 분할 갯수가 지정하지 않았을시 5개씩
		}
		try {
			pgseparatorNum = Integer.parseInt(request.getParameter("pgseparatorNum")); //페이지 분할갯수를 리퀘스트에서 받아서 지정해유
		}catch(Exception e) {
			pgseparatorNum = 5;//페이지 분할 갯수가 지정하지 않았을시 5개씩
		}
		//DB값을 LIST DTO로 받아옴
		List<PostDTO> list= postSer.postSelectALL(); // 쿼리문을 날려서 DB에서 모든 게시판 레코드를 담음  
		
		Paging.AutoPaging(request, response, model, list, pageNum, separatorNum,pgseparatorNum); //모델에 페이징된 리스트와 페이지번호리스트를 자동셋팅해주는 메서드
		
		return "pagingExam";
	}
}
