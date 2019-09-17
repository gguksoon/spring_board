package kr.or.ddit.reply.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyService;

@WebServlet("/deleteReply")
public class DeleteReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private IReplyService replyService;
	
	@Override
	public void init() throws ServletException {
		replyService = new ReplyService();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int postSeq = Integer.parseInt(request.getParameter("postSeq"));
		int replySeq = Integer.parseInt(request.getParameter("replySeq"));
		
		replyService.deleteReply(replySeq);
		
		response.sendRedirect(request.getContextPath() + "/post?postSeq=" + postSeq);
	}

}
