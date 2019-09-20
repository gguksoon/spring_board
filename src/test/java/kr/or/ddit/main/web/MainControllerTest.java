package kr.or.ddit.main.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.config.test.WebTestConfig;

public class MainControllerTest extends WebTestConfig {

	/**
	* Method : mainTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 메인화면 출력 테스트
	*/
	@Test
	public void mainTest() throws Exception {
		mockMvc.perform(get("/main"))
				.andExpect(status().isOk())
				.andExpect(view().name("main"));
	}

}
