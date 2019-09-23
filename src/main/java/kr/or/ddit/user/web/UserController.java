package kr.or.ddit.user.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.model.User;
import kr.or.ddit.user.service.IUserService;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
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
	@GetMapping("login") 
	public String loginView() {
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
	@PostMapping("login")
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
	
	/**
	* Method : logout
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param session
	* @return
	* Method 설명 : 로그아웃 요청
	*/
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션의 모든 내용을 지움
		
		return "redirect:/login";
	}
	
	/**
	* Method : userPicture
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param userId
	* @param response
	* @throws IOException
	* Method 설명 : 유저 사진 요청
	*/
	@RequestMapping("userPicture")
	public void userPicture(String userId, HttpServletResponse response) throws IOException {
		User user = userService.getUser(userId);
		
		ServletOutputStream sos = response.getOutputStream();
		
		File picture = new File(user.getRealFileName());
		FileInputStream fis = new FileInputStream(picture);
		
		byte[] buff = new byte[512];
		int len = 0;
		
		while( (len = fis.read(buff, 0, 512)) != -1 ) {
			sos.write(buff, 0, len);
		}
		
		fis.close();
	}
}
