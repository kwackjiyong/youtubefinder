package com.jiyong.yt.controller;

import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jiyong.yt.dto.UserDTO;
import com.jiyong.yt.service.IUserService;
import com.jiyong.yt.util.CustomValidator;
import com.jiyong.yt.util.SHA256;

@Controller
public class UserController {
	
	@Autowired
	IUserService userSer;
	
	//로그인
	@RequestMapping(value = "/login.ing", method = RequestMethod.POST)
	public void login_do(HttpServletRequest request, HttpServletResponse response, Model model,UserDTO userdto) throws Exception{
		response.setContentType("text/html; charset=UTF-8");
		//id가 있는지 확인하고 해당 id에서 각종 정보를 검색합니다.
		UserDTO selectDTO = userSer.userSelectOne(userdto);
		//아이디가 존재할 때
			try {
			if(selectDTO.getUserPw().equals(userdto.getUserPw())){
				HttpSession session = request.getSession();
				session.setAttribute("userData",selectDTO); //유저 정보 한꺼번에 넣음
				model.addAttribute("userData", selectDTO); // 유저 정보 한꺼번에 넣음
				PrintWriter out = response.getWriter();
				out.println("<script>alert('"+userdto.getUserId()+"님 로그인 성공');</script>");
				out.flush();
			}else {
				System.out.println("DBPW:"+selectDTO.getUserPw()+"\n"+"inputPW:"+userdto.getUserPw());
				PrintWriter out = response.getWriter();
				out.println("<script>alert('패스워드가 틀렸습니다.');</script>");
				out.flush();
			}
			}catch(Exception e) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('"+userdto.getUserId()+"는 존재하지 않는 아이디입니다.');</script>");
			out.flush();
		}finally {
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='index.do'</script>");
			out.flush();
		}
	}
	
	//회원가입
	@RequestMapping(value = "/signup.ing", method = RequestMethod.POST)
	public void siginup_ing(HttpServletRequest request, HttpServletResponse response, Model model, UserDTO userdto) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		try{userSer.userInsert(userdto);// 회원가입
			PrintWriter out = response.getWriter();
			out.println("<script>alert('"+userdto.getUserName()+"님 회원가입 완료를 축하드립니다.');</script>");
			out.flush();
		}catch(Exception e) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원가입 실패');</script>");
			out.flush();
		}
		PrintWriter out = response.getWriter();
		out.println("<script>location.href='index.do'</script>");
		out.flush();
	}
	
	//정보 수정
		@RequestMapping(value = "userModify.ing")
		public void userModify(HttpServletRequest request, HttpServletResponse response, Model model, UserDTO userdto) throws Exception{
			response.setContentType("text/html; charset=UTF-8");
			//id가 있는지 확인하고 해당 id에서 각종 정보를 검색합니다.
			UserDTO selectDTO = userSer.userSelectOne(userdto);
			String modifyPassword = (String)request.getParameter("userPw2");
			System.out.println("변경할비밀번호"+modifyPassword);
			
			//아이디가 존재할 때
				try {
				if(selectDTO.getUserPw().equals(userdto.getUserPw())){
					PrintWriter out = response.getWriter();
					out.println("<script>alert('"+userdto.getUserId()+"님 비밀번호 인증성공');</script>");
					modifyPassword = SHA256.getSHA256(modifyPassword);
					userdto.setUserPw(modifyPassword); //변경할 비밀번호로 바꿈
					System.out.println("정보수정 실행");
					int result =userSer.userUpdate(userdto); //업데이트
					System.out.println("정보수정 실행후");
					if(result == 1) {
						System.out.println("정보수정 성공");
						out.println("<script>alert('"+userdto.getUserId()+"님 정보 수정 성공');</script>");
					}else {
						System.out.println("정보수정 실패");
						out.println("<script>alert('"+userdto.getUserId()+"님 정보 수정 실패');</script>");
					}
					out.flush();
				}else {
					System.out.println("DBPW:"+selectDTO.getUserPw()+"\n"+"inputPW:"+userdto.getUserPw());
					PrintWriter out = response.getWriter();
					out.println("<script>alert('패스워드가 틀렸습니다.');</script>");
					out.flush();
				}
				}catch(Exception e) {
					e.printStackTrace();
				PrintWriter out = response.getWriter();
				out.println("<script>alert('"+userdto.getUserId()+"는 존재하지 않는 아이디입니다.');</script>");
				out.flush();
			}finally {
				PrintWriter out = response.getWriter();
				out.println("<script>location.href='index.do'</script>");
				out.flush();
			}
		}
		
		@RequestMapping(value = "userFavorGroup.ing")
		public void userFavorTagGroup(HttpServletRequest request, HttpServletResponse response, Model model, UserDTO userdto) throws Exception{
			response.setContentType("text/html; charset=UTF-8");
			//id가 있는지 확인하고 해당 id에서 각종 정보를 검색합니다.
			try {
				userdto.setUserId(((UserDTO)request.getSession().getAttribute("userData")).getUserId());
			if(userSer.userModify_FavorGroup(userdto)==1) { //업데이트
				System.out.println("선호 태그그룹 선정 성공");
				HttpSession session = request.getSession();
				UserDTO dto = (UserDTO)request.getSession().getAttribute("userData");
				dto.setFavorTagGroup(userdto.getFavorTagGroup());
				session.setAttribute("userData",dto); //유저 정보 한꺼번에 넣음
			}else {
				System.out.println("선호 태그그룹 선정 실패");
			}
			
			}catch(Exception e){
				PrintWriter out = response.getWriter();
				out.println("<script>alert('로그인이 필요한 컨텐츠입니다.');location.href='index.do'</script>");
				out.flush();
				return;
			}finally {
			
				PrintWriter out = response.getWriter();
				out.println("<script>location.href='tag_my.do'</script>");
				out.flush();
			}
		}
		
	
	//로그아웃 
	@RequestMapping(value = "logout.ing")
	public String logOut(HttpServletRequest request) throws Exception{
		request.getSession().invalidate();
		return "redirect:index.do";
	}
}
