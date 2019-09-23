package kr.or.ddit.post.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.config.test.WebTestConfig;
import kr.or.ddit.post.model.Post;

public class PostDaoTest extends WebTestConfig {

	@Resource(name = "postDao")
	private IPostDao postDao;
	
	/**
	* Method : getPostPagingListTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : 게시글 페이징리스트 반환 테스트
	*/
	@Test
	public void getPostPagingListTest() {
		/***Given***/
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardSeq", 1);
		map.put("page", 1);
		map.put("pagesize", 10);
		
		/***When***/
		List<Map> resultMap = postDao.getPostPagingList(map);

		/***Then***/
		assertEquals(10, resultMap.size());
	}
	
	/**
	* Method : getPostTotalCntTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : 전체 게시글 갯수 반환 테스트
	*/
	@Test
	public void getPostTotalCntTest() {
		/***Given***/

		/***When***/
		int totalCnt = postDao.getPostTotalCnt("1"); 

		/***Then***/
		assertEquals(22, totalCnt);
	}
	
	/**
	* Method : getPostTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : postSeq에 해당하는 post객체 반환 테스트 
	*/
	@Test
	public void getPostTest() {
		/***Given***/

		/***When***/
		Post post = postDao.getPost(1);

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
		int insertCnt = postDao.insertPost(post);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	/**
	* Method : getPostNextSeqTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : 게시글 Seq 생성 테스트 
	*/
	@Test
	public void getPostNextSeqTest() {
		/***Given***/

		/***When***/
//		int postSeq = postDao.getPostNextSeq(ss);

		/***Then***/
//		assertEquals(66, postSeq);
	}
	
	/**
	* Method : getPostGnNextSeqTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : 게시글 Gn Seq 생성 테스트
	*/
	@Test
	public void getPostGnNextSeqTest() {
		/***Given***/

		/***When***/
//		int postGn = postDao.getPostGnNextSeq(ss);

		/***Then***/
//		assertEquals(47, postGn);
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
		int deleteCnt = postDao.deletePost(1);

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
		int updateCnt = postDao.updatePost(post);

		/***Then***/
		assertEquals(1, updateCnt);
	}

}
