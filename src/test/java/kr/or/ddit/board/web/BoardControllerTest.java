package kr.or.ddit.board.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import kr.or.ddit.config.test.WebTestConfig;

public class BoardControllerTest extends WebTestConfig {

	/**
	* Method : manageBoardTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 게시판 관리 페이지 요청
	*/
	@Test
	public void manageBoardTest() throws Exception {
		mockMvc.perform(get("/manageBoard"))
				.andExpect(status().isOk())
				.andExpect(view().name("board/manageBoard"));
	}
	
	/**
	* Method : insertBoardTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 게시판 추가
	*/
	@Test
	public void insertBoardTest() throws Exception {
		mockMvc.perform(get("/insertBoard")
						.param("boardNm", "1")
						.param("userId", "brown")
						.param("boardStatus", "사용"))
		.andExpect(status().is(302))
		.andExpect(view().name("redirect:/manageBoard"));
	}
	
	/**
	* Method : updateBoardTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 게시판 수정
	*/
	@Test
	public void updateBoardTest() throws Exception {
		mockMvc.perform(get("/updateBoard")
						.param("boardSeq", "1")
						.param("boardNm", "nm")
						.param("userId", "brown")
						.param("boardLocation", "1")
						.param("boardStatus", "사용"))
				.andExpect(status().is(302))
				.andExpect(view().name("redirect:/manageBoard"));
	}
	
	/**
	* Method : locationChangeBoardTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 게시판 위치 변경
	*/
	@Test
	public void locationChangeBoardTest() throws Exception {
		mockMvc.perform(get("/locationChangeBoard")
				.param("boardSeq", "1")
				.param("change", "down"))
		.andExpect(status().is(302))
		.andExpect(view().name("redirect:/manageBoard"));
	}

}
