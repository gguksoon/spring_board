package kr.or.ddit.reply.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.reply.model.Reply;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyService;

@WebServlet("/insertReply")
public class InsertReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(InsertReplyController.class);
	
	private IReplyService replyService;
	
	@Override
	public void init() throws ServletException {
		replyService = new ReplyService();
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int boardSeq = Integer.parseInt(request.getParameter("boardSeq"));
		
		int postSeq = Integer.parseInt(request.getParameter("postSeq"));
		String userId = request.getParameter("userId");
		String replyContent = request.getParameter("replyContent");
		
		Reply reply = new Reply(0, postSeq, userId, replyContent, null, null, 1);
		
		logger.debug("{}, {}, {}", postSeq, userId, replyContent);
		
		replyService.insertReply(reply);
		
		response.sendRedirect(request.getContextPath() + "/post?boardSeq=" + boardSeq + "&postSeq=" + postSeq);
	}

}
