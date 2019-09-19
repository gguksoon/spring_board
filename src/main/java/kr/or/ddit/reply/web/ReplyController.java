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
	
	@PostMapping("insertReply")
	public String insertReply(int boardSeq, int postSeq, String userId, String replyContent) {
		Reply reply = new Reply(0, postSeq, userId, replyContent, null, null, 1);

		replyService.insertReply(reply);
		return "redirect:/post?boardSeq=" + boardSeq + "&postSeq=" + postSeq;
	}
	
	@PostMapping("deleteReply")
	public String deleteReply(int postSeq, int replySeq, int boardSeq) {
		logger.debug("boardSeq: {}", boardSeq);
		replyService.deleteReply(replySeq);

		return "redirect:/post?boardSeq=" + boardSeq + "&postSeq=" + postSeq;
	}
}
