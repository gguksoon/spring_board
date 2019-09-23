package kr.or.ddit.post.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.config.test.WebTestConfig;
import kr.or.ddit.post.model.Post;

public class PostServiceTest extends WebTestConfig {

	@Resource(name = "postService")
	private IPostService postService;

	/**
	* Method : getPostPagingListTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : 게시글 페이징리스트 반환 테스트
	*/
	@Test
	public void getPostPagingListTest() {
		/***Given***/
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardSeq", "1");
		map.put("page", 1);
		map.put("pagesize", 10);
		String boardSeq = "1";
		
		/***When***/
		resultMap = postService.getPostPagingList(map);

		/***Then***/
		assertEquals(2, resultMap.size());
	}
	
	/**
	* Method : getPostTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : postSeq에 해당하는 게시글 반환 테스트
	*/
	@Test
	public void getPostTest() {
		/***Given***/
		int postSeq = 1;
		
		/***When***/
		Post post = postService.getPost(postSeq);

		/***Then***/
		assertEquals("첫번째 글입니다", post.getPostNm());
	}
	
	/**
	* Method : insertPostTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : 게시글 추가 테스트
	*/
	@Test
	public void insertPostTest() {
		/***Given***/
		Post post = new Post(0, 1, "테스트", "테스트", "brown", new Date(), new Date(), 1, 0, null);

		/***When***/
		int insertCnt = postService.insertPost(post);

		/***Then***/
		assertEquals(28, insertCnt);
	}
	
	/**
	* Method : deletePostTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : 게시글 삭제 테스트
	*/
	@Test
	public void deletePostTest() {
		/***Given***/
		
		/***When***/
		int deleteCnt = postService.deletePost(1);

		/***Then***/
		assertEquals(deleteCnt, 1);
	}
	
	/**
	* Method : updatePostTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : 게시글 수정 테스트
	*/
	@Test
	public void updatePostTest() {
		/***Given***/
		Post post = new Post(1, "변경테스트", "변경테스트", new Date());
		
		/***When***/
		int updateCnt = postService.updatePost(post);

		/***Then***/
		assertEquals(1, updateCnt);
	}

}
