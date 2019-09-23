package kr.or.ddit.reply.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.config.test.WebTestConfig;
import kr.or.ddit.reply.model.Reply;

public class ReplyDaoTest extends WebTestConfig {

	@Resource(name = "replyDao")
	private IReplyDao replyDao;
	
	/**
	* Method : getReplyListTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : postSeq에 해당하는 댓글리스트 반환 테스트
	*/
	@Test
	public void getReplyListTest() {
		/***Given***/

		/***When***/
		List<Reply> replyList = replyDao.getReplyList(1);

		/***Then***/
		assertEquals(8, replyList.size());
	}
	
	/**
	* Method : insertReplyTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : 댓글 추가 테스트
	*/
	@Test
	public void insertReplyTest() {
		/***Given***/
		Reply reply = new Reply(0, 2, "brown", "테스트내용", new Date(), new Date(), 1);

		/***When***/
		int insertCnt = replyDao.insertReply(reply);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	/**
	* Method : deleteReplyTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : 댓글 삭제 테스트
	*/
	@Test
	public void deleteReplyTest() {
		/***Given***/
		int replySeq = 1; 

		/***When***/
		int deleteCnt = replyDao.deleteReply(replySeq);

		/***Then***/
		assertEquals(1, deleteCnt);
	}

}
