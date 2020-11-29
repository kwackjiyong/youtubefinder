package com.jiyong.yt.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.jiyong.yt.dto.UserDTO;

//유효성 검사를 하는 클래스입니다.
public class CustomValidator {
	
	
	// 구버전 메서드 ###################################################################################################################
	
	//해당페이지에서 이용하는 사용자세션의 클래스타입을 검사하여 강제 페이지 이동을 하는 메서드입니다.
	//세션의 내용을 꼭 필요로 하며 그로인한 작동을 원하는 페이지에서 사용합니다.
	//pageType는 해당 페이지에서 이용가능한 클래스 타입(학생,교수,관리자 등)
	//sessionType는 해당 페이지에서 세션에 저장된 아이디의 클래스타입을 넣어주도록 합니다.
	//response를 통해 해당 응답페이지를 강제로 설정해서 이동합니다.
	public static void checkClassType(int pageType, HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("클래스 검사를 시작합니다");
		response.setContentType("text/html; charset=UTF-8");
		int sessionType=-1; // default를 -1로 설정 
		try {
			//1.요청객체에서 세션을 가져오고 2.세션에서 해당 유저의 클래스타입을 가져온다
		sessionType = (Integer)request.getSession().getAttribute("userClass");
		System.out.println("test_is_root:"+sessionType);
		}catch(Exception e) {//is_root 자체가 존재하지 않을 때는 세션이 존재하지 않았다고 판명 로그인이 되어있지 않은 것
			//e.printStackTrace();
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요한 페이지입니다.');location.href='index.do';</script>");
			out.flush();
			return;
		}
		//페이지타입 0:학생 1:교수 2:관리자
		if(pageType!=sessionType){ // 타입이 맞지않았을 경우 history.back으로 바로 뒤로가기를 해준다.
			PrintWriter out = response.getWriter();
			out.println("<script>alert('권한이 없는 페이지입니다.');location.href='index.do';</script>");
			out.flush();
		}
		System.out.println("클래스 검사를 종료합니다");
	}
	
	//세션이 존재하는지 안하는지 판별 후 존재하는경우 로그인이 되었다 판별하여 해당 세션에서 값을 가져와 모델에 더해주는 역할을 합니다.
	//더해진 model멤버들은 넘어가는 뷰에서 뿌려지고 더해진 request멤버는 컨트롤러 안에서 사용하게됩니다. 
	public static void settingSessionMember(Model model, HttpServletRequest request,HttpServletResponse response) throws Exception{
		String userId ="";
		String userName ="";
		int userClass = -1;
		try {//세션에서 값을 가져옴 
		userId = (String)request.getSession().getAttribute("userId");
		userName = (String)request.getSession().getAttribute("userName");
		userClass = (Integer)request.getSession().getAttribute("userClass");
		}catch(Exception e) {
			e.printStackTrace();
			//경고창 출력
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요합니다.');location.href='index.do';</script>");
			out.flush();
			return;//탈출
		}
		//다음 페이지의 모델에 더해서 해당 다음페이지에서 세션에 들어있는 값을 출력가능케 한다.
		model.addAttribute("userId" ,userId);
		model.addAttribute("userName" ,userName);
		model.addAttribute("userClass" ,userClass);
		//리퀘스트는 단순히 컨트롤러에서 사용하려고 넣었음
		request.setAttribute("userId_setting" ,userId);
		request.setAttribute("userName_setting" ,userName);
		request.setAttribute("userClass_setting" ,userClass);
	}
	
	 //되돌아가는 기능만 없어진 세션 셋팅 메서드입니다.
	 //로그인이 꼭 필요한 페이지는 아니지만 세션이 있을경우 다른 작동을 원하는 페이지에서 사용합니다.
		public static void settingSessionMember_free(Model model, HttpServletRequest request,HttpServletResponse response) throws Exception{
			String userId ="";
			String userName ="";
			int userClass = -1;
			try {//세션에서 값을 가져옴 
				userId = (String)request.getSession().getAttribute("userID");
				userName = (String)request.getSession().getAttribute("userName");
				userClass = (Integer)request.getSession().getAttribute("userClass");
			}catch(Exception e) {
				//e.printStackTrace();
				return;//탈출
			}
			//다음 페이지의 모델에 더해서 해당 다음페이지에서 세션에 들어있는 값을 출력가능케 한다.
			model.addAttribute("userId" ,userId);
			model.addAttribute("userName" ,userName);
			model.addAttribute("userClass" ,userClass);
			//리퀘스트는 단순히 컨트롤러에서 사용하려고 넣었음
			request.setAttribute("userId_setting" ,userId);
			request.setAttribute("userName_setting" ,userName);
			request.setAttribute("userClass_setting" ,userClass);
		}
		
		
		
		// 신규 메서드 ###################################################################################################################
		
		//세션에서 DTO를 받아와서 모델에 다시 DTO를 뿌리는 메서드
		public static void getSessionUserData(Model model, HttpServletRequest request,HttpServletResponse response) throws Exception{
			UserDTO userdto = new UserDTO();
			try {//세션에서 값을 가져옴 
				userdto = (UserDTO)request.getSession().getAttribute("userData");
			}catch(Exception e) {
				//e.printStackTrace();
				return;//탈출
			}
			//다음 페이지의 모델에 더해서 해당 다음페이지에서 세션에 들어있는 값을 출력가능케 한다.
			model.addAttribute("userData" ,userdto);
		}
		
		//세션에서 DTO를 받아와서 모델에 다시 DTO를 뿌리는 메서드
				public static void getSessionUserData_check(Model model, HttpServletRequest request,HttpServletResponse response) throws Exception{
					UserDTO userdto = new UserDTO();
					try {//세션에서 값을 가져옴 
						userdto = (UserDTO)request.getSession().getAttribute("userData");
						if(userdto == null) {
							response.setContentType("text/html; charset=UTF-8");
							PrintWriter out = response.getWriter();
							out.println("<script>alert('해당 컨텐츠는 로그인이 필요합니다.');location.href='index.do';</script>");
							out.flush();
							return;//탈출
						}
					}catch(Exception e) {
						//e.printStackTrace();
						response.setContentType("text/html; charset=UTF-8");
						PrintWriter out = response.getWriter();
						out.println("<script>alert('페이지 오류발생');location.href='index.do';</script>");
						out.flush();
						return;//탈출
					}
					//다음 페이지의 모델에 더해서 해당 다음페이지에서 세션에 들어있는 값을 출력가능케 한다.
					model.addAttribute("userData" ,userdto);
				}
		
		//클래스타입을 체크하는 메서드
		public static void checkSessionClassType(int pageType, HttpServletRequest request,HttpServletResponse response) throws Exception{
			System.out.println("클래스 검사를 시작합니다");
			response.setContentType("text/html; charset=UTF-8");
			int sessionType=-1; // default를 -1로 설정 
			try {
				//1.요청객체에서 세션을 가져오고 2.세션에서 해당 유저의 클래스타입을 가져온다
				UserDTO userdto = (UserDTO)request.getSession().getAttribute("userData");
				
			sessionType = userdto.getUserClass();
			System.out.println("test_is_root:"+sessionType);
			}catch(Exception e) {//is_root 자체가 존재하지 않을 때는 세션이 존재하지 않았다고 판명 로그인이 되어있지 않은 것
				//e.printStackTrace();
				PrintWriter out = response.getWriter();
				out.println("<script>alert('로그인이 필요한 페이지입니다.');location.href='index.do';</script>");
				out.flush();
				return;
			}
			//페이지타입 0:일반 1:관리자
			if(pageType!=sessionType){ // 타입이 맞지않았을 경우 history.back으로 바로 뒤로가기를 해준다.
				PrintWriter out = response.getWriter();
				out.println("<script>alert('권한이 없는 페이지입니다.');location.href='index.do';</script>");
				out.flush();
			}
			System.out.println("클래스 검사를 종료합니다");
		}
		
}
