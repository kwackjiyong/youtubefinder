package com.jiyong.yt.controller;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.javassist.expr.NewArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jiyong.yt.dto.ChannelDTO;
import com.jiyong.yt.dto.PostDTO;
import com.jiyong.yt.dto.TagDTO;
import com.jiyong.yt.dto.UserDTO;
import com.jiyong.yt.service.IChannelService;
import com.jiyong.yt.service.IPostService;
import com.jiyong.yt.service.ITagService;
import com.jiyong.yt.util.CustomValidator;
import com.jiyong.yt.util.Paging;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Autowired
	ITagService tagSer;
	@Autowired
	IPostService postSer;
	@Autowired
	IChannelService chnSer;
	@RequestMapping(value = "/")
	public String home(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception {
		CustomValidator.getSessionUserData(model,request,response);
		return "index";
	}
	
	
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String index_do(HttpServletRequest request, HttpServletResponse response, Model model)throws Exception {
		CustomValidator.getSessionUserData(model,request,response);
		TagDTO dto = new TagDTO();
		if((UserDTO)request.getSession().getAttribute("userData")!=null) {
		UserDTO userdto = 	(UserDTO)request.getSession().getAttribute("userData");
		dto.setUserId(userdto.getUserId());
		List<TagDTO> mytagdtos = tagSer.tagSelectMy(dto);
		List<TagDTO> mytagdtos2 = new ArrayList();
		for(int i = 0; i < mytagdtos.size()&&i<5; i++){
			mytagdtos2.add(mytagdtos.get(i));
		}
		model.addAttribute("MytagDTOs",mytagdtos2);
		}
		//전체 인기 태그 top5 통계
		List<TagDTO> tagdtoNames = tagSer.tagSelectName();
		List<Integer> tagdtoCounts = tagSer.tagSelectCount();
		List<TagDTO> tagdtoHots = new ArrayList();
		for(int i = 0; i < tagdtoNames.size()&&i<5; i++){
			tagdtoNames.get(i).setTagCount(tagdtoCounts.get(i));
			tagdtoNames.get(i).setTagId(i+1);
			tagdtoHots.add(tagdtoNames.get(i));
	    }
		model.addAttribute("tagdtoHots",tagdtoHots);
		
		//주간 인기 태그 top5 통계
		TagDTO weekdto = new TagDTO();
		weekdto.setTaggingDate(new java.sql.Timestamp(new java.util.Date().getTime()-(7*24*60*60*1000)));
		List<TagDTO> tagdtoWeekNames = tagSer.tagSelectWeekName(weekdto);
		List<Integer> tagdtoWeekCounts = tagSer.tagSelectWeekCount(weekdto);
		List<TagDTO> tagdtoWeeks = new ArrayList();
		for(int i = 0; i < tagdtoWeekNames.size()&&i<5; i++){
			tagdtoWeekNames.get(i).setTagCount(tagdtoWeekCounts.get(i));
			tagdtoWeekNames.get(i).setTagId(i+1);
			tagdtoWeeks.add(tagdtoWeekNames.get(i));
	    }
		model.addAttribute("tagdtoWeeks",tagdtoWeeks);
		//공지사항 내용 가져오기
		List<PostDTO> postdtos = postSer.postSelectALL();
		List<PostDTO> postdtos2 = new ArrayList<PostDTO>();
		for(int i = 0; i < postdtos.size()&&i<5; i++){
			postdtos2.add(postdtos.get(i));
		}
		model.addAttribute("postdtos",postdtos2);
		//새로업데이트된 채널가져오기
		List<ChannelDTO> chndtos = chnSer.channelSelectALL();
		List<ChannelDTO> chndtos2 = new ArrayList<ChannelDTO>();
		for(int i = 0; i < chndtos.size()&&i<5; i++){
			chndtos2.add(chndtos.get(i));
		}
		model.addAttribute("chndtos",chndtos2);
		
		
		return "index";
	}
	
	
	
	
	
}
