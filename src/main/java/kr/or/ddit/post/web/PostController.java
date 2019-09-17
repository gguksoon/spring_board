package kr.or.ddit.post.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.Board;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.file.model.File;
import kr.or.ddit.file.service.FileService;
import kr.or.ddit.file.service.IFileService;
import kr.or.ddit.post.model.Post;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;
import kr.or.ddit.reply.model.Reply;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyService;

@WebServlet("/post")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IBoardService boardService;
	private IPostService postService;
	private IReplyService replyService;
	private IFileService fileService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
		postService = new PostService();
		replyService = new ReplyService();
		fileService = new FileService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("boardList", boardService.getBoardList());
		int postSeq = Integer.parseInt(request.getParameter("postSeq"));
		Post post = postService.getPost(postSeq);
		
		request.setAttribute("post", post);

		String boardSeq = request.getParameter("boardSeq");
		Board board = boardService.getBoard(boardSeq);
		
		request.setAttribute("board", board);
		
		List<Reply> replyList = replyService.getReplyList(postSeq);
		request.setAttribute("replyList", replyList);
		
		List<File> fileList = fileService.getFileList(postSeq);
		request.setAttribute("fileList", fileList);
		
		request.getRequestDispatcher("/post.jsp").forward(request, response);
	}

}
