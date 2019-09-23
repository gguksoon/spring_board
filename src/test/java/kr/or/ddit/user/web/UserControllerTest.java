package kr.or.ddit.user.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import kr.or.ddit.config.test.WebTestConfig;

public class UserControllerTest extends WebTestConfig {

	/**
	* Method : loginViewTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 로그인 화면 요청 처리 테스트
	*/
	@Test
	public void loginViewTest() throws Exception {
		mockMvc.perform(get("/login"))
				.andExpect(status().isOk())
				.andExpect(view().name("login/login"));
	}
	
	/**
	* Method : loginProcessTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 로그인 요청 처리 테스트
	*/
	@Test
	public void loginProcessTest() throws Exception {
		mockMvc.perform(post("/login")
						.param("userId", "brown")
						.param("pass", "brown1234"))
				.andExpect(status().is(302))
				.andExpect(view().name("redirect:/main"));
	}
	
	/**
	* Method : logoutTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 로그아웃 요청 테스트
	*/
	@Test
	public void logoutTest() throws Exception {
		mockMvc.perform(get("/logout"))
				.andExpect(status().is(302))
				.andExpect(view().name("redirect:/login"));
	}

	/**
	* Method : userPictureTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 유저 사진 요청 테스트
	*/
	@Test
	public void userPictureTest() throws Exception {
		mockMvc.perform(get("/userPicture")
						.param("userId", "brown"))
				.andExpect(status().isOk());
	}
}
