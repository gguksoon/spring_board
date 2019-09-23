package kr.or.ddit.reply.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

import kr.or.ddit.config.test.WebTestConfig;
import kr.or.ddit.reply.model.Reply;

public class ReplyControllerTest extends WebTestConfig {

	/**
	* Method : insertReplyTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 댓글 추가 테스트
	*/
	@Test
	public void insertReplyTest() throws Exception {
		Reply reply = new Reply(0, 1, "brown", "내용", null, null, 1);
		mockMvc.perform(post("/insertReply")
						.param("boardSeq", "1")
						.param("postSeq", "1")
						.param("userId", "brown")
						.param("replyContent", "내용"))
				.andExpect(status().is(302));
	}

	/**
	* Method : deleteReplyTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 댓글 삭제 테스트 
	*/
	@Test
	public void deleteReplyTest() throws Exception {
		mockMvc.perform(post("/deleteReply")
						.param("boardSeq", "1")
						.param("replySeq", "1")
						.param("postSeq", "1")
						.param("replyContent", "내용"))
				.andExpect(status().is(302));
	}
}
