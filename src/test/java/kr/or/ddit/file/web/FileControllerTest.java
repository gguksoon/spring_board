package kr.or.ddit.file.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import kr.or.ddit.config.test.WebTestConfig;

public class FileControllerTest extends WebTestConfig {

	/**
	* Method : fileDownloadViewTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 파일 다운로드 테스트
	*/
	@Test
	public void fileDownloadViewTest() throws Exception {
		mockMvc.perform(get("/fileDownloadView")
						.param("fileSeq", "1"))
				.andExpect(model().attributeExists("file"));
	}
	
	/**
	* Method : deleteFileTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 파일 삭제 테스트
	*/
	@Test
	public void deleteFileTest() throws Exception {
		mockMvc.perform(post("/deleteFile")
						.param("fileSeq", "1")
						.param("postSeq", "1")
						.param("boardSeq", "1"))
				.andExpect(status().is(302))
				.andExpect(view().name("redirect:/updatePost?boardSeq=1&postSeq=1"));
	}

}
