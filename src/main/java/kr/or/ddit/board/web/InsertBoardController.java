package kr.or.ddit.board.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.Board;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;

@WebServlet("/insertBoard")
public class InsertBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IBoardService boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String boardNm = request.getParameter("boardNm");
		String userId = request.getParameter("userId");
		int boardStatus = request.getParameter("boardStatus").equals("사용") ? 1 : 0;
		
		Board board = new Board(0, boardNm, userId, 0, boardStatus);
		
		boardService.insertBoard(board);
		
		response.sendRedirect(request.getContextPath() + "/manageBoard");
	}

}
