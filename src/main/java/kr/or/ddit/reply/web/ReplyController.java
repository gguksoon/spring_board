package kr.or.ddit.reply.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import kr.or.ddit.reply.model.Reply;
import kr.or.ddit.reply.service.IReplyService;

@Controller
public class ReplyController {

	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@Resource(name = "replyService")
	private IReplyService replyService;
	
	/**
	* Method : insertReply
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param boardSeq
	* @param postSeq
	* @param userId
	* @param replyContent
	* @return
	* Method 설명 : 댓글 추가
	*/
	@PostMapping("insertReply")
	public String insertReply(int boardSeq, int postSeq, String userId, String replyContent) {
		Reply reply = new Reply(0, postSeq, userId, replyContent, null, null, 1);

		replyService.insertReply(reply);
		return "redirect:/post?boardSeq=" + boardSeq + "&postSeq=" + postSeq;
	}
	
	/**
	* Method : deleteReply
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param postSeq
	* @param replySeq
	* @param boardSeq
	* @return
	* Method 설명 : 댓글 삭제
	*/
	@PostMapping("deleteReply")
	public String deleteReply(int postSeq, int replySeq, int boardSeq) {
		logger.debug("boardSeq: {}", boardSeq);
		replyService.deleteReply(replySeq);

		return "redirect:/post?boardSeq=" + boardSeq + "&postSeq=" + postSeq;
	}
}
