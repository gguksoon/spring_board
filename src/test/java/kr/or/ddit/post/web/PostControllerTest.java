package kr.or.ddit.post.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import kr.or.ddit.config.test.WebTestConfig;

public class PostControllerTest extends WebTestConfig {

	/**
	* Method : postPagingListTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 게시글 페이징 리스트 반환 테스트
	*/
	@Test
	public void postPagingListTest() throws Exception {
		mockMvc.perform(get("/postPagingList")
				.param("boardSeq", "1")
				.param("page", "1")
				.param("pagesize", "10"))
		.andExpect(model().attributeExists("boardSeq"))
		.andExpect(model().attributeExists("board"))
		.andExpect(model().attributeExists("pageVo"))
		.andExpect(model().attributeExists("postList"))
		.andExpect(model().attributeExists("paginationSize"))
		.andExpect(status().isOk())
		.andExpect(view().name("post/postPagingList"));
	}
	
	/**
	* Method : postViewTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 게시글 페이지 요청 테스트
	*/
	@Test
	public void postViewTest() throws Exception {
		mockMvc.perform(get("/post")
				.param("postSeq", "1")
				.param("boardSeq", "1"))
		.andExpect(model().attributeExists("post"))
		.andExpect(model().attributeExists("boardSeq"))
		.andExpect(model().attributeExists("board"))
		.andExpect(model().attributeExists("replyList"))
		.andExpect(model().attributeExists("fileList"))
		.andExpect(status().isOk())
		.andExpect(view().name("post/post"));
	}
	
	/**
	* Method : insertPostViewTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 게시글 작성 페이지 요청 테스트
	*/
	@Test
	public void insertPostViewTest() throws Exception {
		mockMvc.perform(get("/insertPost")
						.param("boardSeq", "1")
						.param("postSeq", "1")
						.param("postGn", "1"))
				.andExpect(status().isOk())
				.andExpect(view().name("post/insertPost"));
	}
	
	/**
	* Method : insertPostTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 게시글 추가 테스트
	*/
	@Test
	public void insertPostTest() throws Exception {
		File f = new File("src/test/resources/kr/or/ddit/test/testImg.png");
		FileInputStream fis = new FileInputStream(f);
		
		MockMultipartFile file = new MockMultipartFile("picture", "sally.png", "", fis);
		
		mockMvc.perform(fileUpload("/insertPost")
						.file(file)
						.param("boardSeq", "1")
						.param("userId", "brown")
						.param("postNm", "test")
						.param("postContent", "test"))
				.andExpect(status().is(302));
	}
	
	/**
	* Method : updatePostViewTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 게시글 수정 페이지 요청 테스트
	*/
	@Test
	public void updatePostViewTest() throws Exception {
		mockMvc.perform(get("/updatePost")
				.param("boardSeq", "1")
				.param("postSeq", "1"))
		.andExpect(model().attributeExists("board"))
		.andExpect(model().attributeExists("post"))
		.andExpect(model().attributeExists("fileList"))
		.andExpect(status().isOk())
		.andExpect(view().name("post/updatePost"));
	}
	
	/**
	* Method : updatePostTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 게시글 수정 요청 테스트
	*/
	@Test
	public void updatePostTest() throws Exception {
		File f = new File("src/test/resources/kr/or/ddit/test/testImg.png");
		FileInputStream fis = new FileInputStream(f);
		
		MockMultipartFile file = new MockMultipartFile("picture", "sally.png", "", fis);
		
		mockMvc.perform(fileUpload("/updatePost")
						.file(file)
						.param("boardSeq", "1")
						.param("postSeq", "1")
						.param("postNm", "test")
						.param("postContent", "test"))
				.andExpect(status().is(302))
				.andExpect(view().name("redirect:/post?boardSeq=1&postSeq=1"));
	}
	
	/**
	* Method : deletePostTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 게시글 삭제 테스트
	*/
	@Test
	public void deletePostTest() throws Exception {
		mockMvc.perform(post("/deletePost")
						.param("boardSeq", "1")
						.param("postSeq", "1"))
				.andExpect(status().is(302))
				.andExpect(view().name("redirect:/postPagingList?boardSeq=1"));
	}

}
