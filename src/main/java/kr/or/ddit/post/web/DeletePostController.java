package kr.or.ddit.post.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;

@WebServlet("/deletePost")
public class DeletePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IPostService postService;
	
	@Override
	public void init() throws ServletException {
		postService = new PostService();
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postSeq = Integer.parseInt(request.getParameter("postSeq"));
		int boardSeq = Integer.parseInt(request.getParameter("boardSeq"));
		
		postService.deletePost(postSeq);
		
		response.sendRedirect(request.getContextPath() + "/postPagingList?boardSeq=" + boardSeq);
	}

}
