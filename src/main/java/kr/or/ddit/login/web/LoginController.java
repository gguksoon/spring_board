package kr.or.ddit.login.web;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.model.User;
import kr.or.ddit.user.service.IUserService;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name = "userService")
	private IUserService userService;
	
	@Resource(name = "boardService")
	private IBoardService boardService;
	
	/**
	* Method : view
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @return
	* Method 설명 : 로그인 화면 요청 처리(forward) 
	*/
	@RequestMapping(path = "login", method = RequestMethod.GET) 
	public String view() {
		//TODO cookie 처리는 생략
		
		return "login/login";
	}
	
	/**
	* Method : loginProcess
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param userId
	* @param pass
	* @param rememberMe
	* @param response
	* @param session
	* @return
	* Method 설명 : 로그인 요청 처리 
	*/
	@RequestMapping(path = "login", method = RequestMethod.POST)
	public String loginProcess(String userId, String pass, String rememberMe,
								HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		manageUserIdCookie(response, userId, rememberMe);

		User user = userService.getUser(userId);
		
		if(user == null) {
			return "login/login";
		} else if(user.checkLoginValidate(userId, pass)) {
			// session에 user정보 넣기
			session.setAttribute("S_USERVO", user);

			// application에 boardList 넣기
			ServletContext application = request.getServletContext();
			application.setAttribute("boardList", boardService.getBoardList());

			return "redirect:/main";
		} else {
			return "login/login";
		}
	}
	
	private void manageUserIdCookie(HttpServletResponse response, String userId, String rememberMe) {
		// rememberMe 파라미터가 존재할 경우 userId를 cookie로 생성
		Cookie cookie = new Cookie("userId", userId);
		if(rememberMe != null)
			cookie.setMaxAge(60 * 60 * 24 * 30); // second
		else 
			cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
}
